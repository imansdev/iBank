package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import com.ibank.bankingprocess.model.Account;
import com.ibank.bankingprocess.model.Customer;
import com.ibank.bankingprocess.model.DecryptedAccount;
import com.ibank.bankingprocess.repository.AccountRepository;
import com.ibank.bankingprocess.repository.CustomerRepository;
import com.ibank.bankingprocess.repository.DecryptedAccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DecryptedAccountRepository decryptedAccountRepository;

    public void accountInsertion(AccountInputDTO account) {
        Account newAccount = new Account();
        newAccount.setAccountType(Account.AccountType.valueOf(account.getAccountType()));
        newAccount.setAccountBalanceLimit(account.getAccountBalanceLimit());
        newAccount.setAccountCreationDate(account.getAccountCreationDate());
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBalance(account.getBalance().toString());

        Customer customer = customerRepository.findByCustomerId(account.getCustomerId());

        newAccount.setCustomer(customer);
        accountRepository.save(newAccount);
    }

    public void accountInsertionWithDecryptedData(String decryptedAccountNumber,
            String decryptedBalance, Long customerId) {

        DecryptedAccount decryptedAccount = new DecryptedAccount();
        decryptedAccount.setDecryptedAccountNumber(decryptedAccountNumber);
        decryptedAccount.setDecryptedBalance(Long.parseLong(decryptedBalance));

        Customer customer = customerRepository.findByCustomerId(customerId);

        decryptedAccount.setCustomer(customer);
        decryptedAccountRepository.save(decryptedAccount);
    }

    // Fetch customer with balance greater than 1000 and map to DTOs
    // public List<CustomerAccountOutDTO> getAccountWithBalanceGreaterThan1000() {
    // List<Account> account = accountRepository.findAccountWithBalanceGreaterThan1000();

    // // Map the account to DTOs
    // return account.stream().map(account -> {
    // CustomerAccountOutDTO dto = new CustomerAccountOutDTO();
    // dto.setCustomerId(account.getCustomer().getCustomerId());
    // dto.setAccountType(account.getAccountType().name());
    // dto.setBalance(account.getBalance());
    // dto.setAccountCreationDate(account.getAccountCreationDate());
    // dto.setAccountBalanceLimit(account.getAccountBalanceLimit());
    // return dto;
    // }).collect(Collectors.toList());
    // }
}
