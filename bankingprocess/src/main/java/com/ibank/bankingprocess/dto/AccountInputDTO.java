package com.ibank.bankingprocess.dto;



import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;



public class AccountInputDTO {


    private Long customerId;

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\d{22}", message = "accountNumber must be exactly 22 digits")
    private String accountNumber;

    @NotBlank
    @NotNull
    private Long accountBalanceLimit;

    @NotBlank
    @NotNull
    private String accountType;

    @NotBlank
    @NotNull
    private String balance;


    @NotBlank
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    @Past(message = "accountCreationDate should be in the past")
    private LocalDate accountCreationDate;

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

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

}
