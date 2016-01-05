package com.ej.cache.core.interfaces;

import com.ej.api.CurrencyPair;

import java.math.BigDecimal;

/**
 * The interface for cache management.
 *
 * The {@code CacheManager} interface provides {@code get} method in order to get an actual currency exchange rate from
 * the cache.
 *
 */
public interface CacheManager {

    /**
     * Gets an actual currency exchange rate for the specified currency pair from the cache.
     *
     * @param currencyPair {@link CurrencyPair} object.
     * @return {@code BigDecimal} - an actual currency exchange rate.
     */
    BigDecimal get(CurrencyPair currencyPair);

}
