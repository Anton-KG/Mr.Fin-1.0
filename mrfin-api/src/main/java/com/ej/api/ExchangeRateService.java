package com.ej.api;

import java.math.BigDecimal;

/**
 * The interface of service for the exchange rate.
 *
 */
public interface ExchangeRateService {

    /**
     * Returns the exchange rate.
     *
     * @param currencyPair The currency pair for the exchange rate.
     * @return The exchange rate.
     */
    BigDecimal getExchangeRate(CurrencyPair currencyPair);
}
