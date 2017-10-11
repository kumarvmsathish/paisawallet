package com.vivartha.kryptopal.model;

/**
 * Created by ANANTH on 20-09-2017.
 */

public class Transaction {
    private int transactionImage;
    private String transactionName;
    private String transactionTitle;
    private String transactionDate;
    private String transactionAmount;

    public Integer getTransactionImage() {
        return transactionImage;
    }

    public void setTransactionImage(Integer transactionImage) {
        this.transactionImage = transactionImage;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public String getTransactionTitle() {
        return transactionTitle;
    }

    public void setTransactionTitle(String transactionTitle) {
        this.transactionTitle = transactionTitle;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
