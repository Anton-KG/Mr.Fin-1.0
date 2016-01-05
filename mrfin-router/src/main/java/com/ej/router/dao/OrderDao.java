package com.ej.router.dao;

import com.ej.router.domain.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The interface of dao for save information on conversion.
 */
@Service
public interface OrderDao {
    /**
     * Save information about exchange transaction.
     *
     * @param order Information on conversion.
     * @return The id exchange transaction.
     */
    long saveHistory(Order order);

    /**
     * Save information of the amount of the converted currency.
     *
     * @param amount The amount of currency.
     * @param id The ID of order in TransactionHistoryEntity
     */
    void saveCurrency(long id, BigDecimal amount);
}
