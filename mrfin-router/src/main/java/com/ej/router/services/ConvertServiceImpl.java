package com.ej.router.services;

import com.ej.api.ExchangeRateService;
import com.ej.router.dao.OrderDao;
import com.ej.router.domain.Order;
import com.ej.router.exceptions.RouterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Class ConvertServiceImpl is responsible for convertion of needed amount of currency.
 * Also this class connected with DaoImp and provide adding data to db of current {@code order}.
 */
@Service
public class ConvertServiceImpl implements ConvertService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private ExchangeRateService exchangeRateService;

    /**
     * The default constructor.
     */
    public ConvertServiceImpl() {
    }

    /**
     * @param order Information on conversion.
     * @return {@code BigDecimal} the result of convertetion.
     */
    @Override
    public BigDecimal convert(Order order) {
        if (order == null) {
            throw new RouterException("Invalid argument for order ");
        }
        long currentId = setCurrentOrder(order);
        BigDecimal rate = exchangeRateService.getExchangeRate(order.getCurrencyPair());
        if (rate == null || rate.doubleValue() <= 0) {
            throw new RouterException();
        }
        BigDecimal convertedAmount = order.getAmount().multiply(rate);
        setQuotedAmount(currentId, convertedAmount);
        return convertedAmount;
    }
    private long setCurrentOrder(Order order) {
        return orderDao.saveHistory(order);
    }

    private void setQuotedAmount(long id, BigDecimal amount) {
        orderDao.saveCurrency(id, amount);

    }
}
