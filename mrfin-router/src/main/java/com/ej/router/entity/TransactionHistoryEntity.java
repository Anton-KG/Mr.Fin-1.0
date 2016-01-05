package com.ej.router.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Transaction History.
 */
@Entity
@Table(name = "transaction_history")
public class TransactionHistoryEntity implements Serializable {
    @Id
    @Column(name = "id_transaction_history")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idTransactionHistory;

    @Column(name = "data_transaction")
    @Temporal(TemporalType.DATE)
    private Date dataTransaction;

    @Column(name = "base_currency")
    private String baseCurrency;

    @Column(name = "quote_currency")
    private String quoteCurrency;

    @Column(name = "base_amount")
    private BigDecimal baseAmount;

    @Column(name = "browser_name")
    private String browserName;

    /**
     * Default constructor.
     */
    public TransactionHistoryEntity() {
    }

    /**
     * Get the ID of transaction.
     *
     * @return ID of transaction.
     */
    public long getIdTransactionHistory() {
        return idTransactionHistory;
    }

    /**
     * To set ID of transaction.
     *
     * @param idTransactionHistory ID of transaction.
     */
    public void setIdTransactionHistory(long idTransactionHistory) {
        this.idTransactionHistory = idTransactionHistory;
    }

    /**
     * Get the Data Transaction.
     *
     * @return The Data Transaction.
     */
    public Date getDataTransaction() {
        return dataTransaction;
    }

    /**
     * To set data of transaction.
     *
     * @param dataTransaction Data of transaction.
     */
    public void setDataTransaction(Date dataTransaction) {
        this.dataTransaction = dataTransaction;
    }

    /**
     * Get the Base Currency.
     *
     * @return The Base Currency.
     */
    public String getBaseCurrency() {
        return baseCurrency;
    }

    /**
     * To set Base Currency.
     *
     * @param baseCurrency Base Currency.
     */
    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    /**
     * Get the Quote Currency.
     *
     * @return The Quote Currency.
     */
    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    /**
     * To set Quote Currency.
     *
     * @param quoteCurrency Quote Currency.
     */
    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency;
    }

    /**
     * Get the Base Amount.
     *
     * @return The Base Amount.
     */
    public BigDecimal getBaseAmount() {
        return baseAmount;
    }

    /**
     * To set Base Amount.
     *
     * @param baseAmount Base Amount.
     */
    public void setBaseAmount(BigDecimal baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * Get the Browser Name.
     *
     * @return The Browser Name.
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * To set Browser Name.
     *
     * @param browserName Browser Name.
     */
    public void setBrowserName(String browserName) {
        this.browserName = browserName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransactionHistoryEntity)) {
            return false;
        }
        TransactionHistoryEntity that = (TransactionHistoryEntity) o;
        if (idTransactionHistory != that.idTransactionHistory) {
            return false;
        }
        if (dataTransaction == null ? that.dataTransaction != null : !dataTransaction.equals(that.dataTransaction)) {
            return false;
        }
        if (baseCurrency == null ? that.baseCurrency != null : !baseCurrency.equals(that.baseCurrency)) {
            return false;
        }
        if (quoteCurrency == null ? that.quoteCurrency != null : !quoteCurrency.equals(that.quoteCurrency)) {
            return false;
        }
        if (baseAmount == null ? that.baseAmount != null : !baseAmount.equals(that.baseAmount)) {
            return false;
        }
        if (browserName == null ? that.browserName != null : !browserName.equals(that.browserName)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int hash = 31;
        final int hash2 = 32;
        int result = (int) (idTransactionHistory ^ (idTransactionHistory >>> hash2));
        result = hash * result + (dataTransaction != null ? dataTransaction.hashCode() : 0);
        result = hash * result + (baseCurrency != null ? baseCurrency.hashCode() : 0);
        result = hash * result + (quoteCurrency != null ? quoteCurrency.hashCode() : 0);
        result = hash * result + (baseAmount != null ? baseAmount.hashCode() : 0);
        result = hash * result + (browserName != null ? browserName.hashCode() : 0);
        return result;
    }
}
