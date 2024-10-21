package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import com.ibank.bankingprocess.exception.NoAccountsFoundException;
import com.ibank.bankingprocess.model.Account;
import com.ibank.bankingprocess.model.Customer;
import com.ibank.bankingprocess.model.DecryptedAccount;
import com.ibank.bankingprocess.repository.AccountRepository;
import com.ibank.bankingprocess.repository.CustomerRepository;
import com.ibank.bankingprocess.repository.DecryptedAccountRepository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DecryptedAccountRepository decryptedAccountRepository;

    @Autowired
    private FileWriterFactory fileWriterFactory;

    @Value("${balance.threshold}")
    private Long balanceThreshold;

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

    public List<CustomerAccountOutDTO> getCustomersWithSpecifiedBalance() {
        List<DecryptedAccount> accounts =
                decryptedAccountRepository.findByDecryptedBalanceGreaterThan(balanceThreshold);

        return accounts.stream().map(account -> {
            CustomerAccountOutDTO dto = new CustomerAccountOutDTO();
            dto.setAccountNumber(account.getDecryptedAccountNumber());
            dto.setCustomerId(account.getCustomer().getCustomerId());
            dto.setBalance(account.getDecryptedBalance());
            return dto;
        }).collect(Collectors.toList());
    }

    // Method to save data to a file based on the format
    public List<CustomerAccountOutDTO> saveToFile(String format) throws IOException {

        List<CustomerAccountOutDTO> accounts = getCustomersWithSpecifiedBalance();

        if (accounts == null || accounts.isEmpty()) {
            throw new NoAccountsFoundException(
                    "No customer's accounts were found with a balance greater than the "
                            + balanceThreshold);
        }

        // Get the appropriate file writer using the factory
        FileWriterStrategy fileWriter = fileWriterFactory.getFileWriter(format);

        // Write the accounts to the file using the selected strategy
        fileWriter.writeToFile(accounts);

        return accounts;
    }
}
