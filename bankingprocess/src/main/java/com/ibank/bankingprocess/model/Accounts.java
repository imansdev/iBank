package com.ibank.bankingprocess.model;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private Long accountBalanceLimit;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(nullable = false)
    private String balance;

    @Column(nullable = false, updatable = false)
    private LocalDate accountCreationDate;

    // @ManyToOne
    // @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customerId")
    // private Customers customer;

    public enum AccountType {
        SAVINGS, RD, FD
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getAccountBalanceLimit() {
        return accountBalanceLimit;
    }

    public void setAccountBalanceLimit(Long accountBalanceLimit) {
        this.accountBalanceLimit = accountBalanceLimit;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
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

    public Long getId() {
        return id;
    }

    // public void setCustomer(Customers customer) {
    // this.customer = customer;
    // }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Accounts [id=" + id + ", accountNumber=" + accountNumber + ", accountBalanceLimit="
                + accountBalanceLimit + ", accountType=" + accountType + ", balance=" + balance
                + ", accountCreationDate=" + accountCreationDate + "]";
    }

}
