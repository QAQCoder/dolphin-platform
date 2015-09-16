package com.canoo.dolphin.client.v2;

import com.canoo.dolphin.Constants;
import com.canoo.dolphin.client.ClientConfiguration;
import com.canoo.dolphin.client.impl.ClientContextImpl;
import org.opendolphin.core.client.ClientDolphin;
import org.opendolphin.core.client.ClientModelStore;
import org.opendolphin.core.client.comm.HttpClientConnector;
import org.opendolphin.core.comm.JsonCodec;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by hendrikebbers on 14.09.15.
 */
public class ClientContextFactory {

    private ClientContextFactory() {
    }

    public static CompletableFuture<ClientContext> connect(ClientConfiguration clientConfiguration) {
        final CompletableFuture<ClientContext> result = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                final ClientDolphin dolphin = new ClientDolphin();
                dolphin.setClientModelStore(new ClientModelStore(dolphin));
                final HttpClientConnector clientConnector = new HttpClientConnector(dolphin, clientConfiguration.getServerEndpoint());
                clientConnector.setCodec(new JsonCodec());
                clientConnector.setUiThreadHandler(clientConfiguration.getUiThreadHandler());
                dolphin.setClientConnector(clientConnector);
                ClientContext clientContext = new ClientContextImpl(dolphin);
                dolphin.startPushListening(Constants.POLL_COMMAND_NAME, Constants.RELEASE_COMMAND_NAME);
                clientConfiguration.getUiThreadHandler().executeInsideUiThread(() -> result.complete(clientContext));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return result;
    }

}
