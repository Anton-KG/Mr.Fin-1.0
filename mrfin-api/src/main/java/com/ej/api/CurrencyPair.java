package com.ej.api;

import java.io.Serializable;

/**
 * The structure of data contains currency pair.
 */
public class CurrencyPair implements Serializable {
    private static final int BASE_HASH_CODE = 31;
    private String baseCurrency;
    private String quoteCurrency;

    /**
     * Constructs a {@code CurrencyPair}.
     */
    public CurrencyPair() {
    }

    /**
     * Initializes a newly created {@code CurrencyPair} object.
     *
     * @param isoCodeBase  The ISO code of base currency.
     * @param isoCodeQuote The ISO code of quote currency.
     */
    public CurrencyPair(String isoCodeBase, String isoCodeQuote) {
        baseCurrency = isoCodeBase;
        quoteCurrency = isoCodeQuote;
    }

    /**
     * Return the ISO code of base currency.
     *
     * @return The ISO code of base currency.
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * To set the ISO code of base currency.
     *
     * @param isoCode The ISO code of base currency.
     */
    public void setBaseCurrency(String isoCode) {
        baseCurrency = isoCode;
    }

    /**
     * Return the ISO code of quote currency.
     *
     * @return The ISO code of quote currency.
     */
    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    /**
     * To set the ISO code of quote currency.
     *
     * @param isoCode The ISO code of quote currency.
     */
    public void setQuoteCurrency(String isoCode) {
        quoteCurrency = isoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof CurrencyPair)) {
            return false;
        }

        CurrencyPair that = (CurrencyPair) o;

        return baseCurrency.equals(that.baseCurrency) && quoteCurrency.equals(that.quoteCurrency);
    }

    @Override
    public int hashCode() {
        return BASE_HASH_CODE * baseCurrency.hashCode() + quoteCurrency.hashCode();
    }

    @Override
    public String toString() {
        return baseCurrency + "/" + quoteCurrency;
    }
}
