package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.repository.CustomersRepository;

@Service
public class CustomersService {
    @Autowired
    private CustomersRepository customersRepository;
}
