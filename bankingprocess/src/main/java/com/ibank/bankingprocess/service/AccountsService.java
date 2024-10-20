package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import com.ibank.bankingprocess.model.Accounts;
import com.ibank.bankingprocess.model.Customers;
import com.ibank.bankingprocess.repository.AccountsRepository;
import com.ibank.bankingprocess.repository.CustomersRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    @Autowired
    private CustomersRepository customersRepository;

    public void accountInsertion(AccountInputDTO account) {
        Accounts newAccount = new Accounts();
        newAccount.setAccountType(Accounts.AccountType.valueOf(account.getAccountType()));
        newAccount.setAccountBalanceLimit(account.getAccountBalanceLimit());
        newAccount.setAccountCreationDate(account.getAccountCreationDate());
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBalance(account.getBalance().toString());

        Customers customer = customersRepository.findByCustomerId(account.getCustomerId());

        newAccount.setCustomer(customer);
        accountsRepository.save(newAccount);
    }

    // Fetch customers with balance greater than 1000 and map to DTOs
    public List<CustomerAccountOutDTO> getAccountsWithBalanceGreaterThan1000() {
        List<Accounts> accounts = accountsRepository.findAccountsWithBalanceGreaterThan1000();

        // Map the accounts to DTOs
        return accounts.stream().map(account -> {
            CustomerAccountOutDTO dto = new CustomerAccountOutDTO();
            dto.setCustomerId(account.getCustomer().getCustomerId());
            dto.setAccountType(account.getAccountType().name());
            dto.setBalance(account.getBalance());
            dto.setAccountCreationDate(account.getAccountCreationDate());
            dto.setAccountBalanceLimit(account.getAccountBalanceLimit());
            return dto;
        }).collect(Collectors.toList());
    }
}
