package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import com.ibank.bankingprocess.model.Accounts;
import com.ibank.bankingprocess.repository.AccountsRepository;

@Service
public class AccountsService {

    @Autowired
    private AccountsRepository accountsRepository;

    public void accountInsertion(AccountInputDTO account) {
        Accounts newAccount = new Accounts();
        newAccount.setAccountType(Accounts.AccountType.valueOf(account.getAccountType()));
        newAccount.setAccountBalanceLimit(account.getAccountBalanceLimit());
        newAccount.setAccountCreationDate(account.getAccountCreationDate());
        newAccount.setAccountNumber(account.getAccountNumber());
        newAccount.setBalance(account.getBalance().toString());

        accountsRepository.save(newAccount);
    }
}
