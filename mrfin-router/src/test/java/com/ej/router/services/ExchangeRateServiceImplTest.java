package com.ej.router.services;

import com.ej.api.CurrencyPair;
import com.ej.api.RestRequestSpring;
import com.ej.router.exceptions.CacheResponseException;
import com.ej.router.services.ExchangeRateServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withServerError;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {MockServletContext.class})
public class ExchangeRateServiceImplTest {
    private String url="http://localhost:8080/retrieve/{base}/{quote}";

    private MockRestServiceServer mockServer;
    private ExchangeRateServiceImpl exchangeRateService;

    @Before
    public void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);

        RestRequestSpring restRequestSpring = new RestRequestSpring();
        ReflectionTestUtils.setField(restRequestSpring, "rest", restTemplate);

        exchangeRateService = new ExchangeRateServiceImpl();
        ReflectionTestUtils.setField(exchangeRateService, "request", restRequestSpring);
        ReflectionTestUtils.setField(exchangeRateService, "url", url);
    }

    @Test
    public void testUSDtoUAH() {
        CurrencyPair currencyPair = new CurrencyPair("USD", "UAH");

        mockServer.expect(requestTo(getUrlPath(currencyPair)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess("{\"Status\" : 200, \"ExchangeRate\" : \"1.5\", \"Message\" : \"\"}",
                                MediaType.APPLICATION_JSON)
                );


        BigDecimal rate = exchangeRateService.getExchangeRate(new CurrencyPair("USD", "UAH"));
        assertEquals(rate.doubleValue(), 1.5, 0.01);

        mockServer.verify();
    }

    @Test(expected = CacheResponseException.class)
    public void testUSDtoBADandHttpStatus() {
        CurrencyPair currencyPair = new CurrencyPair("USD", "BAD");

        mockServer.expect(requestTo(getUrlPath(currencyPair)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withServerError());

        exchangeRateService.getExchangeRate(currencyPair);

        mockServer.verify();
    }

    @Test(expected = CacheResponseException.class)
    public void testUSDtoBAD() {
        CurrencyPair currencyPair = new CurrencyPair("USD", "BAD");

        mockServer.expect(requestTo(getUrlPath(currencyPair)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess("{\"Status\" : 404, \"ExchangeRate\" : \"\", \"Message\" : \"Error\"}",
                                MediaType.APPLICATION_JSON)
                );

        exchangeRateService.getExchangeRate(currencyPair);

        mockServer.verify();
    }

    @Test(expected = CacheResponseException.class)
    public void testUSDtoBADnoMessage() {
        CurrencyPair currencyPair = new CurrencyPair("USD", "BAD");

        mockServer.expect(requestTo(getUrlPath(currencyPair)))
                .andExpect(method(HttpMethod.GET))
                .andRespond(
                        withSuccess("{\"Status\" : 404, \"ExchangeRate\" : \"\", \"Message\" : \"\"}",
                                MediaType.APPLICATION_JSON)
                );

        exchangeRateService.getExchangeRate(currencyPair);

        mockServer.verify();
    }

    private String getUrlPath(CurrencyPair currencyPair) {
        String tagBase = "{base}";
        String tagQuote = "{quote}";

        StringBuilder sb = new StringBuilder(url);

        int indexBase = sb.indexOf(tagBase);
        if (indexBase != -1) {
            sb.replace(indexBase, indexBase + tagBase.length(), currencyPair.getBaseCurrency());
        }

        int indexQuote = sb.indexOf(tagQuote);
        if (indexQuote != -1) {
            sb.replace(indexQuote, indexQuote + tagQuote.length(), currencyPair.getQuoteCurrency());
        }

        return sb.toString();
    }
}
