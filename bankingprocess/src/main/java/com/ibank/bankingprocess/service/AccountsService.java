package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.repository.AccountsRepository;

@Service
public class AccountsService {
    @Autowired
    private AccountsRepository accountsRepository;
}
