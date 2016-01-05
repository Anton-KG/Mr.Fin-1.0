package com.ej.sh1rk.exceptions;

/**
 * {@code Sh1rkException} is the superclass of those
 * exceptions that can be thrown during the operation of the
 * Sh1rk Framework.
 * <p>
 * {@code Sh1rkException} its subclasses are {@code RuntimeException}.
 */
public class Sh1rkException extends RuntimeException {
    /**
     * Constructs a new exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public Sh1rkException(String message) {
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
    public Sh1rkException(String message, Throwable cause) {
        super(message, cause);
    }
}
