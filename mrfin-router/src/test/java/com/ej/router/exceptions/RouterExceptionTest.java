package com.ej.router.exceptions;

import org.junit.Test;

/**
 * Test fo {@link RouterException}.
 */
public class RouterExceptionTest {

    @Test(expected = RouterException.class)
    public void testException() {
        throw new RouterException();
    }
}