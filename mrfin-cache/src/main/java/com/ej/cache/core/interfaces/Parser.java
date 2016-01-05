package com.ej.cache.core.interfaces;

import com.ej.api.CurrencyPair;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The interface for parsing the response from bank.
 *
 * The {@code Parser} interface provides {@code parse} method in order to parse a response from the bank server.
 *
 */
public interface Parser {

    /**
     * Parses the response from the bank server.
     *
     * @param responseString {@code String} with the response from the bank server.
     * @return {@code Map} with the currency pairs and their exchange rates.
     */
    Map<CurrencyPair, BigDecimal> parse(String responseString);

}
