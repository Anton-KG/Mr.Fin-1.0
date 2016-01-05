package com.ej.router.dao;

import com.ej.router.domain.Order;
import com.ej.router.entity.ConvertedCurrencyEntity;
import com.ej.router.entity.TransactionHistoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class for saving information on a conversion.
 *
 * Implements {@link OrderDao} interface.
 */
@Transactional
@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager em;

    /**
     * An empty constructor for Hibernate compatibility.
     */
    public OrderDaoImpl() {
    }

    @Override
    public long saveHistory(Order order) {
        TransactionHistoryEntity entity = new TransactionHistoryEntity();
        entity.setBaseCurrency(order.getCurrencyPair().getBaseCurrency());
        entity.setQuoteCurrency(order.getCurrencyPair().getQuoteCurrency());
        entity.setBrowserName(order.getBrowserName());
        entity.setBaseAmount(order.getAmount());
        entity.setDataTransaction(new Date());
        this.em.persist(entity);
        return entity.getIdTransactionHistory();
    }

    @Override
    public void saveCurrency(long id, BigDecimal amount) {
        ConvertedCurrencyEntity convertedCurrencyEntity = new ConvertedCurrencyEntity();
        TransactionHistoryEntity transactionHistoryEntity = this.em.find(TransactionHistoryEntity.class, id);
        convertedCurrencyEntity.setTransactionHistoryEntity(transactionHistoryEntity);
        convertedCurrencyEntity.setQuoteAmount(amount);
        this.em.persist(convertedCurrencyEntity);
    }
}
