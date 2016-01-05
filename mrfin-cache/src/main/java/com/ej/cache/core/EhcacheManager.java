package com.ej.cache.core;

import com.ej.api.CurrencyPair;
import com.ej.api.exceptions.CacheValidationException;
import com.ej.cache.core.interfaces.CacheManager;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.math.BigDecimal;
import java.util.Map;

/**
 * {@link EhcacheManager} is a class for Ehcache management.
 *
 * Implements the {@link CacheManager} interface.
 *
 */
@Component
@EnableScheduling
public class EhcacheManager implements CacheManager {

    private static final Logger LOG = LoggerFactory.getLogger(EhcacheManager.class);

    private static final int CACHE_REFRESH_DELAY = 900000;
    private static net.sf.ehcache.CacheManager cacheManager;
    private static Cache cache;
    @Autowired
    private PrivatBankJsonParser parser;
    @Autowired
    private PrivatBankAPI api;

    @PostConstruct
    private void init() {
        cacheManager = net.sf.ehcache.CacheManager.getInstance();
        cache = cacheManager.getCache("bank");
        putCurrencyPairsToCache();
    }

    @Scheduled(fixedDelay = CACHE_REFRESH_DELAY)
    private void putCurrencyPairsToCache() {
        Map<CurrencyPair, BigDecimal> map = parser.parse(api.getResponse());
        cache.removeAll();
        LOG.info("Refreshing currency exchange rates in cache...");
        for (Map.Entry<CurrencyPair, BigDecimal> e : map.entrySet()) {
            cache.put(new Element(e.getKey(), e.getValue()));
        }
    }

    @Override
    public BigDecimal get(CurrencyPair currencyPair) {
        try {
            Element element = cache.get(currencyPair);
            return (BigDecimal) element.getObjectValue();
        } catch (NullPointerException e) {
            LOG.error(e.getMessage());
            throw new CacheValidationException("Bad request - unable to get the exchange rate. Please check your " +
                    "request and try again later");
        }
    }

    @PreDestroy
    private void cleanUp() {
        cacheManager.shutdown();
    }

}
