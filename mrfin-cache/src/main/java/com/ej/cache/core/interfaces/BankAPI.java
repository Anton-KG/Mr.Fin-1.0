package com.ej.cache.core.interfaces;

/**
 * The interface for communication with the bank.
 *
 * The {@code BankAPI} interface provides {@code getResponse} method in order to receive a response from the bank
 * server with the actual currency exchange rates.
 *
 */
public interface BankAPI {

    /**
     * Communicates with the bank server in order to get actual currency exchange rates.
     *
     * @return the {@code String} containing the response from the bank server with actual currency exchange rates
     */
    String getResponse();
}
