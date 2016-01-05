package com.ej.router.services;

import com.ej.api.CacheResponse;
import com.ej.api.CurrencyPair;
import com.ej.api.ExchangeRateService;
import com.ej.api.RestRequest;
import com.ej.router.exceptions.CacheResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.math.BigDecimal;

/**
 * The {@code ExchangeRateServiceImpl} class implements an interface ExchangeRateService.
 */
@Service("exchangeRateService")
public class ExchangeRateServiceImpl implements ExchangeRateService {
    @Value("${cache.url}")
    private String url;

    @Autowired
    private RestRequest request;

    /**
     * Returns the exchange rate.
     *
     * @param currencyPair The currency pair for the exchange rate.
     * @return The exchange rate.
     */
    public BigDecimal getExchangeRate(CurrencyPair currencyPair) {
        CacheResponse response;

        try {
            response = request.getEntity(url,
                    CacheResponse.class,
                    currencyPair.getBaseCurrency(),
                    currencyPair.getQuoteCurrency())
                    .getBody();
        } catch (RestClientException e) {
            throw new CacheResponseException(e.getMessage());
        }

        if (response.getStatus() != HttpStatus.OK.value()) {
            throw new CacheResponseException(response.getMessage());
        }

        return new BigDecimal(response.getExchangeRate());
    }
}
