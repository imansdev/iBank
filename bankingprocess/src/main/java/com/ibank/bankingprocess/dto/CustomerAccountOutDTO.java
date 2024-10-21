package com.ibank.bankingprocess.dto;

public class CustomerAccountOutDTO {

    private String accountNumber;
    private Long customerId;
    private Long balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "CustomerAccountDTO [accountNumber=" + accountNumber + ", customerId=" + customerId
                + ", balance=" + balance + "]";
    }
}
