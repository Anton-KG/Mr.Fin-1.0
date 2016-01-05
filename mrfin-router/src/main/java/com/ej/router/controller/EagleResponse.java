package com.ej.router.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Class {@code EagleResponse} for Json representation of ResponseEntity.
 */
public class EagleResponse {

    private String convertedAmount;
    private String message;
    private String status;
    /**
     * Return the value of {@code status}.
     * @return The value of exchange rate.
     */
    @JsonGetter("status")
    public String getStatus() {
        return status;
    }
    /**
     * To set the {@code status}.
     * @param statusHttp HttpStatus.
     */
    @JsonSetter("status")
    public void setStatus(String statusHttp) {
        this.status = statusHttp;
    }
    /**
     * Default constructor.
     */
    EagleResponse() { }
    /**
     * Return the value of exchange rate.
     * @return The value of exchange rate.
     */
    @JsonGetter("convertedAmount")
    public String getConvertedAmount() {
        return convertedAmount;
    }
    /**
     * To set the value of exchange rate.
     *
     * @param value The value of exchange rate
     */
    @JsonSetter("convertedAmount")
    public void setConvertedAmount(String value) {
        convertedAmount = value;
    }
    /**
     * Return the message.
     *
     * @return The message.
     */
    @JsonGetter("message")
    public String getMessage() {
        return message;
    }
    /**
     * To set the message.
     * @param text The message.
     */
    @JsonSetter("message")
    public void setMessage(String text) {
        message = text;
    }
}
