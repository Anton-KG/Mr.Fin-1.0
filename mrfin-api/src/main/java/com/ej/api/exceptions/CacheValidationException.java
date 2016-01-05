package com.ej.api.exceptions;

/**
 * {@link CacheValidationException} is the class of those exception which can be thrown during the operation of the
 * cache layer of MrFin Framework.
 *
 * The subclass of {@link CacheValidationException} is {@link MrFinValidationException}.
 *
 */
public class CacheValidationException extends MrFinValidationException {

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     * {@link #getMessage()} method.
     */
    public CacheValidationException(String message) {
        super(message);
    }
}
