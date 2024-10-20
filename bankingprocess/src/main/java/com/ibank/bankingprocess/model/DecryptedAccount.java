package com.ibank.bankingprocess.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
public class DecryptedAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String decryptedAccountNumber;

    @Column(nullable = false)
    private String decryptedBalance;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customerId")
    private Customers customer;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDecryptedAccountNumber() {
        return decryptedAccountNumber;
    }

    public void setDecryptedAccountNumber(String decryptedAccountNumber) {
        this.decryptedAccountNumber = decryptedAccountNumber;
    }

    public String getDecryptedBalance() {
        return decryptedBalance;
    }

    public void setDecryptedBalance(String decryptedBalance) {
        this.decryptedBalance = decryptedBalance;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }
}
