package com.ej.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * The {@code RestRequestSpring} class implements an interface RestRequest.
 */
@Component
public class RestRequestSpring implements RestRequest {
    private final RestTemplate rest = new RestTemplate();

    @Override
    public <T> T getObject(String url, Class<T> responseType, Object... urlVariables) {
        return rest.getForObject(url, responseType, urlVariables);
    }

    @Override
    public <T> ResponseEntity<T> getEntity(String url, Class<T> responseType, Object... urlVariables) {
        return rest.getForEntity(url, responseType, urlVariables);
    }

}
