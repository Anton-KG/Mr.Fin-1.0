package com.ej.router.services;

import com.ej.router.domain.Order;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * The interface of service for currency conversion.
 */
@Service
public interface ConvertService {
    /**
     * The method exchange currency.
     *
     * @param order Information on conversion.
     * @return The amount of the exchange currency.
     */
    BigDecimal convert(Order order);
}
