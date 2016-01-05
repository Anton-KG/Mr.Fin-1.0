package com.ej.router.services;

import com.ej.api.CurrencyPair;
import com.ej.api.ExchangeRateService;
import com.ej.router.dao.OrderDao;
import com.ej.router.domain.Order;
import com.ej.router.exceptions.RouterException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * ConvertServiceImplTest is test-class which is responsible for tests for {@link ConvertService} class.
 */
public class ConvertServiceImplTest {
    private static final int AMOUNT = 100;
    private ConvertService convertService;
    private Order testOrder;
    private CurrencyPair testCurrencyPair;

    @Before
    public void setUp() {
        convertService = new ConvertServiceImpl();

        testCurrencyPair = new CurrencyPair("USD", "UAH");
        testOrder = new Order();
        testOrder.setCurrencyPair(testCurrencyPair);
        testOrder.setAmount(new BigDecimal(AMOUNT));
        testOrder.setBrowserName("Chrome");
    }

    @Test(expected = RouterException.class)
    public void testInvalidOrderArgs() {
        convertService.convert(null);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionOrderDao() {
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(anyObject())).thenThrow(RuntimeException.class);
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        convertService.convert(testOrder);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionExchangeRateService() {
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(anyObject())).thenReturn(1L);
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        when(exchangeRateService.getExchangeRate(anyObject())).thenThrow(RuntimeException.class);
        ReflectionTestUtils.setField(convertService, "exchangeRateService", exchangeRateService);

        convertService.convert(testOrder);
    }


    @Test(expected = RouterException.class)
    public void testInvalidRateServiceArgs() {
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(anyObject())).thenReturn(1L);
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        when(exchangeRateService.getExchangeRate(anyObject())).thenReturn(null);
        ReflectionTestUtils.setField(convertService, "exchangeRateService", exchangeRateService);

        convertService.convert(testOrder);
    }

    @Test(expected = RouterException.class)
    public void testInvalidRateServiceArgs2() {
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(anyObject())).thenReturn(1L);
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        when(exchangeRateService.getExchangeRate(testCurrencyPair)).thenReturn(new BigDecimal(-1));
        ReflectionTestUtils.setField(convertService, "exchangeRateService", exchangeRateService);

        convertService.convert(testOrder);
    }

    @Test(expected = RuntimeException.class)
    public void testExceptionOrderDaoSaveCurrency() {
        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(anyObject())).thenReturn(1L);
        doThrow(new RuntimeException()).when(orderDao).saveCurrency(anyLong(), anyObject());
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        when(exchangeRateService.getExchangeRate(testCurrencyPair)).thenReturn(new BigDecimal(1.5));
        ReflectionTestUtils.setField(convertService, "exchangeRateService", exchangeRateService);

        convertService.convert(testOrder);
    }

    @Test
    public void testConvertMethod() {

        OrderDao orderDao = Mockito.mock(OrderDao.class);
        when(orderDao.saveHistory(testOrder)).thenReturn(1L);
        ReflectionTestUtils.setField(convertService, "orderDao", orderDao);

        ExchangeRateService exchangeRateService = Mockito.mock(ExchangeRateService.class);
        when(exchangeRateService.getExchangeRate(testCurrencyPair)).thenReturn(new BigDecimal(1.5));
        ReflectionTestUtils.setField(convertService, "exchangeRateService", exchangeRateService);

        double result = new BigDecimal(AMOUNT).multiply(new BigDecimal(1.5)).doubleValue();

        assertEquals("The resuling amount should be equals",
                result, convertService.convert(testOrder).doubleValue(), 0.01);
    }
}