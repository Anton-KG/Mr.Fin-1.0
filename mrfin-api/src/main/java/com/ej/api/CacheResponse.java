package com.ej.api;


import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * The class contains the response data from the Mr.Fin-Cache.
 */
public class CacheResponse {
    private int httpStatus;
    private String exchangeRate;
    private String message;

    /**
     * Constructs a {@code CacheResponse}.
     */
    public CacheResponse() {
    }

    /**
     * Return the value of http status.
     *
     * @return The value of http status
     */
    @JsonGetter("Status")
    public int getStatus() {
        return httpStatus;
    }

    /**
     * To set the value of http status.
     *
     * @param status The value of http status.
     */
    @JsonSetter("Status")
    public void setStatus(int status) {
        httpStatus = status;
    }

    /**
     * Return the value of exchange rate.
     *
     * @return The value of exchange rate.
     */
    @JsonGetter("ExchangeRate")
    public String getExchangeRate() {
        return exchangeRate;
    }

    /**
     * To set the value of exchange rate.
     *
     * @param value The value of exchange rate
     */
    @JsonSetter("ExchangeRate")
    public void setExchangeRate(String value) {
        exchangeRate = value;
    }

    /**
     * Return the message.
     *
     * @return The message.
     */
    @JsonGetter("Message")
    public String getMessage() {
        return message;
    }

    /**
     * To set the message.
     *
     * @param text The message.
     */
    @JsonSetter("Message")
    public void setMessage(String text) {
        message = text;
    }
}
