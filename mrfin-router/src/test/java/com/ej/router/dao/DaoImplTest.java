package com.ej.router.dao;

import com.ej.api.CurrencyPair;
import com.ej.router.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfigTest.class})
public class DaoImplTest {

    @Autowired
    OrderDao orderDao;

    @Test
    public void saveOrder() {
        long id;
        Order o = new Order();
        CurrencyPair currencyPair = new CurrencyPair();
        currencyPair.setBaseCurrency("UAH");
        currencyPair.setQuoteCurrency("USD");
        o.setAmount(new BigDecimal("100"));
        o.setBrowserName("Opera");
        o.setCurrencyPair(currencyPair);
        id = orderDao.saveHistory(o);
        System.out.println(id);
        orderDao.saveCurrency(id, new BigDecimal("250"));
    }
}