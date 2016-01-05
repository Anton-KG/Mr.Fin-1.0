package com.ej.router.exceptions;
/**
 * Class RouterException is responsible for Exceptions which are could be thrown from Router module.
 */
public class RouterException extends RuntimeException {
    /**
     * The default constructor.
     */
    public RouterException() {
        super();
    }
    /**
     * Constructor.
     * @param s {@code String} message of error.
     */
    public RouterException(String s) {
        super(s);
    }
}
