package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibank.bankingprocess.dto.CustomerInputDTO;
import com.ibank.bankingprocess.model.Address;
import com.ibank.bankingprocess.model.Customer;
import com.ibank.bankingprocess.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void customerInsertion(CustomerInputDTO customer) {
        Customer newCustomer = new Customer();
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

        customerRepository.save(newCustomer);
    }
}
