package com.ej.sh1rk.exceptions;

/**
 * {@code Sh1rkParserException} is the class of exception
 * that can be thrown during the operation of the parsing string
 * which contains xml or json data.
 * <p>
 * {@code Sh1rkParserException} its subclasses are {@code Sh1rkException}.
 */
public class Sh1rkParserException extends Sh1rkException {
    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public Sh1rkParserException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and
     * cause.  <p>Note that the detail message associated with
     * {@code cause} is <i>not</i> automatically incorporated in
     * this exception's detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link #getMessage()} method).
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method).  (A <tt>null</tt> value is
     *                permitted, and indicates that the cause is nonexistent or
     *                unknown.)
     */
    public Sh1rkParserException(String message, Throwable cause) {
        super(message, cause);
    }
}
