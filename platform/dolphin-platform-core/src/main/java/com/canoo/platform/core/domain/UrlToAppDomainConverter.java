package com.canoo.platform.core.domain;

import org.apiguardian.api.API;

import java.net.URI;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@API(since = "0.x", status = EXPERIMENTAL)
public interface UrlToAppDomainConverter {

    String getApplicationDomain(URI url);
}
