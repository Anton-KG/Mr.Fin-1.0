package com.ej.cache.services;

import com.ej.api.CurrencyPair;
import com.ej.api.ExchangeRateService;
import com.ej.cache.core.EhcacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * {@link ExchangeRateServiceImpl} is a class that makes currency exchange rate, based on information from cache.
 *
 * Implements the {@link ExchangeRateService} interface.
 *
 */
@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final String BASE_CURRENCY = "UAH";

    @Autowired
    private EhcacheManager ehcacheManager;

    @Override
    public BigDecimal getExchangeRate(CurrencyPair currencyPair) {
        String src = currencyPair.getBaseCurrency().toUpperCase();
        currencyPair.setBaseCurrency(src);
        String dst = currencyPair.getQuoteCurrency().toUpperCase();
        currencyPair.setQuoteCurrency(dst);
        if (!src.equals(BASE_CURRENCY) && !dst.equals(BASE_CURRENCY)) {
            BigDecimal srcRate = ehcacheManager.get(new CurrencyPair(src, BASE_CURRENCY));
            BigDecimal dstRate = ehcacheManager.get(new CurrencyPair(BASE_CURRENCY, dst));
            return srcRate.divide(dstRate, MathContext.DECIMAL32);
        } else {
            BigDecimal rateFromCache = ehcacheManager.get(currencyPair);
            if (currencyPair.getBaseCurrency().equals(BASE_CURRENCY)) {
                return (new BigDecimal(1)).divide(rateFromCache, MathContext.DECIMAL32);
            } else {
                return rateFromCache;
            }
        }
    }

}
