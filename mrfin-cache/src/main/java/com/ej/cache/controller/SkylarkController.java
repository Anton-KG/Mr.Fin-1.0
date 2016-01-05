package com.ej.cache.controller;

import com.ej.api.CacheResponse;
import com.ej.api.CurrencyPair;
import com.ej.api.ExchangeRateService;
import com.ej.api.exceptions.CacheValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;

/**
 * {@link SkylarkController} is the main controller of the {@code Skylark} cache layer of Mr.Fin Framework. It handles
 * the requests and provides the appropriate actual currency exchange rates.
 *
 */
@RestController
@RequestMapping(value = "/retrieve", method = {RequestMethod.GET})
public class SkylarkController {

    private static final Logger LOG = LoggerFactory.getLogger(SkylarkController.class);

    private static final int HTTP_STATUS_OK = 200;
    private static final int HTTP_STATUS_BAD_REQUEST = 400;

    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * Method handles the request mapped to {@code /retrieve} URL.
     *
     * @param srcCurrency ISO code of base currency.
     * @param dstCurrency ISO code of quote currency.
     * @return {@code CacheResponse} object with appropriate actual currency exchange rate.
     */
    @RequestMapping(value = "{srcCurrency}/{dstCurrency}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public CacheResponse retrieve(@PathVariable("srcCurrency") String srcCurrency, @PathVariable("dstCurrency")
    String dstCurrency) {
        CurrencyPair currencyPair = new CurrencyPair(srcCurrency, dstCurrency);
        CacheResponse cacheResponse = new CacheResponse();
        try {
            BigDecimal exchangeRate = exchangeRateService.getExchangeRate(currencyPair);
            cacheResponse.setStatus(HTTP_STATUS_OK);
            cacheResponse.setExchangeRate(exchangeRate.toString());
            cacheResponse.setMessage("");
        } catch (CacheValidationException e) {
            LOG.error(e.getMessage(), e);
            cacheResponse.setStatus(HTTP_STATUS_BAD_REQUEST);
            cacheResponse.setExchangeRate("");
            cacheResponse.setMessage(e.toString());
        }
        return cacheResponse;
    }

}
