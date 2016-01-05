package com.ej.router.exceptions;

/**
 * {@code CacheResponseException} is the class of exception that can be thrown
 * if the request to exchange rate service returned an error.
 * <p>
 * {@code CacheResponseException} its subclasses are {@code RuntimeException}.
 */
public class CacheResponseException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public CacheResponseException(String message) {
        super(message);
    }
}
