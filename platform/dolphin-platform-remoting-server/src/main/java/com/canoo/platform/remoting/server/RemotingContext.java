/*
 * Copyright 2015-2018 Canoo Engineering AG.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.canoo.platform.remoting.server;

import com.canoo.platform.remoting.BeanManager;
import com.canoo.platform.remoting.server.binding.PropertyBinder;
import com.canoo.platform.remoting.server.event.RemotingEventBus;
import com.canoo.platform.server.client.ClientSession;
import org.apiguardian.api.API;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

/**
 * Facade to get access to all instances of a Dolphin Platform remoting context. Each {@link ClientSession} that uses the remoting layer of Dolphin Platform will contain exactly one remoting context.
 *
 * This is a util interface that normally is not needed for application developers since all needed parts of the context can be injected directly in Dolphin Platform controller classes or other managed beans.
 *
 * @author Hendrik Ebbers
 */
@API(since = "0.x", status = EXPERIMENTAL)
public interface RemotingContext {

    /**
     * Return the id of the context.
     * @return the id
     */
    String getId();

    /**
     * Return the executor for the context
     * @return the executor
     */
    ClientSessionExecutor createSessionExecutor();

    /**
     * Return the binder for the context
     * @return the binder
     */
    PropertyBinder getBinder();

    /**
     * Returns the bean manager for the context
     * @return the bean manager
     */
    BeanManager getBeanManager();

    /**
     * Returns the event bus for the context
     * @return the event bus
     */
    RemotingEventBus getEventBus();

    /**
     * Returns the client session for the context
     * @return the client session
     */
    ClientSession getClientSession();

    /**
     * Returns {@code true} if the current thread is in a remoting request of this context, otherwise false;
     * @return {@code true} if the current thread is in a request of this context
     */
    boolean isActive();
}
