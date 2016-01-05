package com.ej.router.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Converted Currency.
 */
@Entity
@Table(name = "converted_currency")
public class ConvertedCurrencyEntity implements Serializable {
    @Id
    @Column(name = "id_converted_currency")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idConvertedCurrency;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_transaction_history")
    private TransactionHistoryEntity transactionHistoryEntity;

    @Column(name = "quote_amount")
    private BigDecimal quoteAmount;


    /**
     * Default constructor.
     */
    public ConvertedCurrencyEntity() {
    }

    /**
     * Get the ID of transaction.
     *
     * @return ID of transaction.
     */
    public long getId() {
        return idConvertedCurrency;
    }

    /**
     * To set ID of transaction.
     *
     * @param idConvCurrency ID of transaction.
     */
    public void setId(long idConvCurrency) {
        this.idConvertedCurrency = idConvCurrency;
    }

    /**
     * Get the Base TransactionHistoryEntity.
     *
     * @return TransactionHistoryEntity.
     */
    public TransactionHistoryEntity getTransactionHistoryEntity() {
        return transactionHistoryEntity;
    }

    /**
     * To set Transaction History Entity.
     *
     * @param transactionHistoryEntity Transaction History Entity.
     */
    public void setTransactionHistoryEntity(TransactionHistoryEntity transactionHistoryEntity) {
        this.transactionHistoryEntity = transactionHistoryEntity;
    }

    /**
     * Get the Base Amount.
     *
     * @return The Base Amount.
     */
    public BigDecimal getQuoteAmount() {
        return quoteAmount;
    }

    /**
     * To set Base Amount.
     *
     * @param quoteAmount Base Amount.
     */
    public void setQuoteAmount(BigDecimal quoteAmount) {
        this.quoteAmount = quoteAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ConvertedCurrencyEntity)) {
            return false;
        }

        ConvertedCurrencyEntity that = (ConvertedCurrencyEntity) o;

        if (idConvertedCurrency != that.idConvertedCurrency) {
            return false;
        }

        if (quoteAmount == null ? that.quoteAmount != null : !quoteAmount.equals(that.quoteAmount)) {
            return false;
        }

        if (transactionHistoryEntity == null ? that.transactionHistoryEntity != null
                : !transactionHistoryEntity.equals(that.transactionHistoryEntity)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        final int hash = 32;
        final int hash2 = 31;
        int result = (int) (idConvertedCurrency ^ (idConvertedCurrency >>> hash));
        result = hash2 * result + (quoteAmount != null ? quoteAmount.hashCode() : 0);
        result = hash * result + (transactionHistoryEntity != null ? transactionHistoryEntity.hashCode() : 0);
        return result;
    }
}
