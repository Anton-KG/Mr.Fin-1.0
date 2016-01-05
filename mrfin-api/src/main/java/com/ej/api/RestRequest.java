package com.ej.api;

import org.springframework.http.ResponseEntity;

/**
 * Interface to communicate with HTTP-servers, according to the principles of REST.
 */
public interface RestRequest {
    /**
     * Retrieve a representation by doing a GET on the specified URL.
     * The response (if any) is converted and returned.
     * <p>URI Template variables are expanded using the given URI variables, if any.
     *
     * @param url          the URL
     * @param responseType the type of the return value
     * @param uriVariables the variables to expand the template
     * @param <T>          Parameterized type.
     * @return the converted object
     */
    <T> T getObject(String url, Class<T> responseType, Object... uriVariables);

    /**
     * Retrieve an entity by doing a GET on the specified URL.
     * The response is converted and stored in an {@link ResponseEntity}.
     * <p>
     * URI Template variables are expanded using the given URI variables, if any.
     *
     * @param url          The URL.
     * @param responseType The type of the return value.
     * @param uriVariables The variables to expand the template.
     * @param <T>          Parameterized type.
     * @return The entity.
     */
    <T> ResponseEntity<T> getEntity(String url, Class<T> responseType, Object... uriVariables);
}
