package com.ej.sh1rk.exceptions;

/**
 * {@code Sh1rkIncorrectFormatException} is the class of exception
 * which can be thrown out during the selection of the correct parser.
 * <p>
 * {@code Sh1rkParserException} its subclasses are {@code Sh1rkException}.
 */
public class Sh1rkIncorrectFormatException extends Sh1rkException {
    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public Sh1rkIncorrectFormatException(String message) {
        super(message);
    }
}
