package com.ej.sh1rk.exceptions;

/**
 * {@link Sh1rkSyntaxException} is the class of exception that can be thrown during the operation of {@code Item}
 * building from XML/JSON parsers.
 * The superclass of {@link Sh1rkSyntaxException} class is {@link Sh1rkException}.
 */
public class Sh1rkSyntaxException extends Sh1rkException {

    /**
     * Constructs a new exception with the specified detail message. The cause is not initialized, and may
     * subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *                {@link #getMessage()} method.
     */
    public Sh1rkSyntaxException(String message) {
        super(message);
    }
}
