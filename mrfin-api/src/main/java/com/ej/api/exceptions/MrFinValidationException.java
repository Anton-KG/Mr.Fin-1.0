package com.ej.api.exceptions;

/**
 * {@link MrFinValidationException} is the superclass of those exceptions that can be thrown during the operation of the
 * MrFin Framework.
 *
 * The subclass of {@link MrFinValidationException} is {@link RuntimeException}.
 *
 */
public class MrFinValidationException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     * {@link #getMessage()} method.
     */
    public MrFinValidationException(String message) {
        super(message);
    }
}
