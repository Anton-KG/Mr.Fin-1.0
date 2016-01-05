package com.ej.router.entity;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TransactionHistoryEntityTest {
    private static final long ID = 1;
    private static final BigDecimal AMOUNT = new BigDecimal(100);
    private static final String QUOTE_CURRENCY = "USD";
    private static final String BASE_CURRENCY = "UAH";
    private static final String BROWSER_NAME = "Chrome";
    private static final Date DATE = new Date();
    private TransactionHistoryEntity transactionHistoryEntity1;

    public static void setUp(TransactionHistoryEntity transactionHistoryEntity) {
        transactionHistoryEntity.setIdTransactionHistory(ID);
        transactionHistoryEntity.setDataTransaction(DATE);
        transactionHistoryEntity.setBaseCurrency(BASE_CURRENCY);
        transactionHistoryEntity.setQuoteCurrency(QUOTE_CURRENCY);
        transactionHistoryEntity.setBaseAmount(AMOUNT);
        transactionHistoryEntity.setBrowserName(BROWSER_NAME);
    }

    @Test
    public void testEquals() {
        transactionHistoryEntity1 = new TransactionHistoryEntity();
        setUp(transactionHistoryEntity1);

        assertEquals(ID, transactionHistoryEntity1.getIdTransactionHistory());
        assertEquals(DATE, transactionHistoryEntity1.getDataTransaction());
        assertEquals(BASE_CURRENCY, transactionHistoryEntity1.getBaseCurrency());
        assertEquals(QUOTE_CURRENCY, transactionHistoryEntity1.getQuoteCurrency());
        assertEquals(AMOUNT, transactionHistoryEntity1.getBaseAmount());
        assertEquals(BROWSER_NAME, transactionHistoryEntity1.getBrowserName());

        assertTrue(transactionHistoryEntity1.equals(transactionHistoryEntity1));

        assertFalse(transactionHistoryEntity1.equals(new Object()));
        assertFalse(transactionHistoryEntity1.equals(null));

        TransactionHistoryEntity transactionHistoryEntity2 = new TransactionHistoryEntity();

        transactionHistoryEntity2.setIdTransactionHistory(2);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setIdTransactionHistory(transactionHistoryEntity1.getIdTransactionHistory());

        transactionHistoryEntity1.setDataTransaction(null);
        transactionHistoryEntity2.setDataTransaction(DATE);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));

        transactionHistoryEntity1.setDataTransaction(DATE);
        transactionHistoryEntity2.setDataTransaction(null);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setDataTransaction(transactionHistoryEntity1.getDataTransaction());

        transactionHistoryEntity1.setBaseCurrency(null);
        transactionHistoryEntity2.setBaseCurrency("USD");
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));

        transactionHistoryEntity1.setBaseCurrency("USD");
        transactionHistoryEntity2.setBaseCurrency(null);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setBaseCurrency(transactionHistoryEntity1.getBaseCurrency());

        transactionHistoryEntity1.setQuoteCurrency(null);
        transactionHistoryEntity2.setQuoteCurrency("UAH");
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));

        transactionHistoryEntity1.setQuoteCurrency("UAH");
        transactionHistoryEntity2.setQuoteCurrency(null);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setQuoteCurrency(transactionHistoryEntity1.getQuoteCurrency());

        transactionHistoryEntity1.setBaseAmount(null);
        transactionHistoryEntity2.setBaseAmount(AMOUNT);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));

        transactionHistoryEntity1.setBaseAmount(AMOUNT);
        transactionHistoryEntity2.setBaseAmount(null);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setBaseAmount(transactionHistoryEntity1.getBaseAmount());

        transactionHistoryEntity1.setBrowserName(null);
        transactionHistoryEntity2.setBrowserName("Chrome");
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));

        transactionHistoryEntity1.setBrowserName("Chrome");
        transactionHistoryEntity2.setBrowserName(null);
        assertFalse(transactionHistoryEntity1.equals(transactionHistoryEntity2));
        transactionHistoryEntity2.setBrowserName(transactionHistoryEntity1.getBrowserName());

        assertTrue(transactionHistoryEntity1.equals(transactionHistoryEntity2));
    }

    @Test
    public void testHash() {
        transactionHistoryEntity1 = new TransactionHistoryEntity();
        setUp(transactionHistoryEntity1);
        TransactionHistoryEntity transactionHistoryEntity2 = new TransactionHistoryEntity();
        setUp(transactionHistoryEntity2);

        assertEquals(transactionHistoryEntity1.hashCode(), transactionHistoryEntity2.hashCode());
    }
}
