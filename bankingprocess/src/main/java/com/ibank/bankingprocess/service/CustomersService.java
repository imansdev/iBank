package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibank.bankingprocess.dto.CustomerInputDTO;
import com.ibank.bankingprocess.model.Address;
import com.ibank.bankingprocess.model.Customers;
import com.ibank.bankingprocess.repository.CustomersRepository;

@Service
public class CustomersService {

    @Autowired
    private CustomersRepository customersRepository;

    public void customerInsertion(CustomerInputDTO customer) {
        Customers newCustomer = new Customers();
        newCustomer.setName(customer.getName());
        newCustomer.setSurname(customer.getSurname());
        newCustomer.setNationalId(customer.getNationalId());
        newCustomer.setDateOfBirth(customer.getDateOfBirth());
        newCustomer.setCustomerId(customer.getCustomerId());

        Address address = new Address();
        address.setStreet(customer.getStreet());
        address.setCity(customer.getCity());
        address.setZipCode(customer.getZipCode());

        newCustomer.setAddress(address);

        customersRepository.save(newCustomer);
    }
}
