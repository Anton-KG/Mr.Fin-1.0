package com.ej.router.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConvertedCurrencyEntityTest {
    private static final long ID = 1;
    private static final BigDecimal AMOUNT = new BigDecimal(100);
    private ConvertedCurrencyEntity convertedCurrencyEntity1;
    private TransactionHistoryEntity transactionHistoryEntity;

    private void setUp(ConvertedCurrencyEntity convertedCurrencyEntity) {
        convertedCurrencyEntity.setId(ID);
        convertedCurrencyEntity.setQuoteAmount(AMOUNT);
        transactionHistoryEntity = new TransactionHistoryEntity();
        TransactionHistoryEntityTest.setUp(transactionHistoryEntity);
        convertedCurrencyEntity.setTransactionHistoryEntity(transactionHistoryEntity);
    }

    @Test
    public void convertedCurrencyEntityTest() {
        convertedCurrencyEntity1 = new ConvertedCurrencyEntity();
        setUp(convertedCurrencyEntity1);

        assertEquals(ID, convertedCurrencyEntity1.getId());
        assertEquals(AMOUNT, convertedCurrencyEntity1.getQuoteAmount());
        assertEquals(transactionHistoryEntity, convertedCurrencyEntity1.getTransactionHistoryEntity());

        assertTrue(convertedCurrencyEntity1.equals(convertedCurrencyEntity1));

        assertFalse(convertedCurrencyEntity1.equals(new Object()));
        assertFalse(convertedCurrencyEntity1.equals(null));

        ConvertedCurrencyEntity convertedCurrencyEntity2 = new ConvertedCurrencyEntity();

        convertedCurrencyEntity2.setId(2);
        assertFalse(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));
        convertedCurrencyEntity2.setId(convertedCurrencyEntity1.getId());

        convertedCurrencyEntity1.setQuoteAmount(null);
        convertedCurrencyEntity2.setQuoteAmount(AMOUNT);
        assertFalse(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));

        convertedCurrencyEntity1.setQuoteAmount(AMOUNT);
        convertedCurrencyEntity2.setQuoteAmount(null);
        assertFalse(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));
        convertedCurrencyEntity2.setQuoteAmount(convertedCurrencyEntity1.getQuoteAmount());

        TransactionHistoryEntity transactionHistoryEntity = new TransactionHistoryEntity();
        TransactionHistoryEntityTest.setUp(transactionHistoryEntity);

        convertedCurrencyEntity1.setTransactionHistoryEntity(null);
        convertedCurrencyEntity2.setTransactionHistoryEntity(transactionHistoryEntity);
        assertFalse(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));

        convertedCurrencyEntity1.setTransactionHistoryEntity(transactionHistoryEntity);
        convertedCurrencyEntity2.setTransactionHistoryEntity(null);
        assertFalse(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));
        convertedCurrencyEntity2.setTransactionHistoryEntity(convertedCurrencyEntity1.getTransactionHistoryEntity());

        assertTrue(convertedCurrencyEntity1.equals(convertedCurrencyEntity2));
    }

    @Test
    public void testHash() {
        convertedCurrencyEntity1 = new ConvertedCurrencyEntity();
        setUp(convertedCurrencyEntity1);
        ConvertedCurrencyEntity convertedCurrencyEntity2 = new ConvertedCurrencyEntity();
        setUp(convertedCurrencyEntity2);

        assertEquals(convertedCurrencyEntity1.hashCode(), convertedCurrencyEntity2.hashCode());
    }
}
