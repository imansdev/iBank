package com.ibank.bankingprocess.dto;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class CustomerInputDTO {

    @NotBlank
    @NotNull
    private Long customerId;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    @NotNull
    private String surname;

    @NotBlank
    @NotNull
    @Pattern(regexp = "\\d{10}", message = "accountNumber must be exactly 10 digits")
    private String nationalId;

    @NotBlank
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    @Past(message = "accountCreationDate should be in the past")
    private LocalDate dateOfBirth;

    private String street;
    private String city;
    private Long zipCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }


    // @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private Accounts account;


}
