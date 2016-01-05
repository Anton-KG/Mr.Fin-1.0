package com.ej.sh1rk.exceptions;

/**
 * {@code Sh1rkTransformerException} is the class of exception
 * that can be thrown during the operation of the transformation of entity{@link com.ej.sh1rk.Entity}
 * <p>
 * {@code Sh1rkTransformerException} is a subclass of {@code Sh1rkException}.
 */

public class Sh1rkTransformerException extends Sh1rkException {
    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public Sh1rkTransformerException(String message) {
        super(message);
    }
}
