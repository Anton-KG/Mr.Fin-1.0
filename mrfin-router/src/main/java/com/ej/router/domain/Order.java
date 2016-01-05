package com.ej.router.domain;

import com.ej.api.CurrencyPair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Information on conversion.
 */
@Service
public class Order {
    private String browserName;
    private CurrencyPair currencyPair;
    private BigDecimal amount;

    /**
     * Default constructor.
     */
    public Order() {
    }

    /**
     * Get currency pair.
     *
     * @return The currency pair.
     */
    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    /**
     * To set currency pair.
     *
     * @param pair The currency pair.
     */
    public void setCurrencyPair(CurrencyPair pair) {
        currencyPair = pair;
    }

    /**
     * Get the amount of currency.
     *
     * @return The amount of currency.
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * To set the amount of currency.
     *
     * @param amountCurrency The amount of currency.
     */
    public void setAmount(BigDecimal amountCurrency) {
        amount = amountCurrency;
    }

    /**
     * Get the name of browser.
     *
     * @return The name of browser.
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * To set the name of browser.
     *
     * @param name The name of browser.
     */
    public void setBrowserName(String name) {
        browserName = name;
    }
}
