package com.ibank.bankingprocess.dto;

import java.time.LocalDate;

public class CustomerAccountOutDTO {

    private Long customerId;
    private String accountType;
    private String balance;
    private LocalDate accountCreationDate;
    private Long accountBalanceLimit;

    // Getters and Setters
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public LocalDate getAccountCreationDate() {
        return accountCreationDate;
    }

    public void setAccountCreationDate(LocalDate accountCreationDate) {
        this.accountCreationDate = accountCreationDate;
    }

    public Long getAccountBalanceLimit() {
        return accountBalanceLimit;
    }

    public void setAccountBalanceLimit(Long accountBalanceLimit) {
        this.accountBalanceLimit = accountBalanceLimit;
    }
}
