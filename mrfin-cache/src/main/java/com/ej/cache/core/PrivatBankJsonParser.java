package com.ej.cache.core;

import com.ej.api.CurrencyPair;
import com.ej.cache.core.interfaces.Parser;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link PrivatBankJsonParser} is a class to parse a response from the Privatbank of Ukraine with an actual currency
 * exchange rates.
 *
 * Implements the {@link Parser} interface.
 *
 */
@Component
public class PrivatBankJsonParser implements Parser {

    @Override
    public Map<CurrencyPair, BigDecimal> parse(String responseString) {
        List<BankCurrencyRate> rates;
        Gson gson = new Gson();
        Type collectionType = new TypeToken<List<BankCurrencyRate>>() {
        }.getType();
        rates = gson.fromJson(responseString, collectionType);
        Map<CurrencyPair, BigDecimal> ratesMap = new HashMap<>();
        for (BankCurrencyRate rate : rates) {
            ratesMap.put(new CurrencyPair(rate.ccy, rate.baseCcy), rate.buy);
            ratesMap.put(new CurrencyPair(rate.baseCcy, rate.ccy), rate.sale);
        }
        return ratesMap;
    }

    /**
     * {@link BankCurrencyRate} is a temporary class for storing the information parsed from the bank response
     * {@code String}.
     */
    private class BankCurrencyRate {
        private String ccy;
        @SerializedName("base_ccy")
        private String baseCcy;
        private BigDecimal buy;
        private BigDecimal sale;
    }

}
