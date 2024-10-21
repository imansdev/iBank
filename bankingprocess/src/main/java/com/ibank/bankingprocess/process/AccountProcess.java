package com.ibank.bankingprocess.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import com.ibank.bankingprocess.service.AccountService;
import com.ibank.bankingprocess.utils.EncryptionUtil;
import com.ibank.bankingprocess.process.error.ErrorLogger;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class AccountProcess {

    @Autowired
    private AccountService accountService;

    @Autowired
    private Validator validator;

    @Autowired
    private ErrorLogger errorLogger;

    @Async
    public CompletableFuture<Void> processFile(String accountCsv) {

        try (Stream<String> lines = Files.lines(Paths.get(accountCsv))) {
            lines.skip(1).parallel().forEach(line -> this.processLine(line, accountCsv));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }

    // The logic for processing each line and performing decryption/validation
    private void processLine(String line, String fileName) {
        String[] fields = line.split(",");

        try {
            int recordNumber = Integer.parseInt(fields[0]);
            if (fields.length != 7
                    || Arrays.stream(fields).anyMatch(field -> field.trim().isEmpty())) {
                logErrorshand(fileName, recordNumber, "NULL_FIELD", "Field",
                        "One or more fields are null or contain only spaces.");
                return;
            }
            String accountNumber = fields[1];
            Long accountBalanceLimit = Long.parseLong(fields[2]);
            String accountType = fields[3];
            String balance = fields[4];
            LocalDate accountCreationDate = LocalDate.parse(fields[5]);
            Long customerId = Long.parseLong(fields[6]);

            String decryptedAccountNumber = EncryptionUtil.decrypt(accountNumber);
            String decryptedBalance = EncryptionUtil.decrypt(balance);

            AccountInputDTO account = new AccountInputDTO();
            account.setAccountNumber(decryptedAccountNumber);
            account.setBalance(decryptedBalance);
            account.setAccountType(accountType);
            account.setAccountBalanceLimit(accountBalanceLimit);
            account.setAccountCreationDate(accountCreationDate);
            account.setCustomerId(customerId);

            // Validate the account object
            Set<ConstraintViolation<AccountInputDTO>> violations = validator.validate(account);

            if (!violations.isEmpty()) {
                // If there are validation errors, log them to error.json
                logErrors(violations, fileName, recordNumber);
            } else {
                account.setAccountNumber(EncryptionUtil.encrypt(accountNumber));
                account.setBalance(EncryptionUtil.encrypt(balance.toString()));
                accountService.accountInsertion(account);

                accountService.accountInsertionWithDecryptedData(decryptedAccountNumber,
                        decryptedBalance, customerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Log validation errors using the ErrorLogger
    private void logErrors(Set<ConstraintViolation<AccountInputDTO>> violations, String fileName,
            int recordNumber) {
        List<Map<String, Object>> errorList = new ArrayList<>();
        for (ConstraintViolation<AccountInputDTO> violation : violations) {
            Map<String, Object> errorDetails =
                    errorLogger.createErrorDetails(fileName, recordNumber,
                            violation.getConstraintDescriptor().getAnnotation().annotationType()
                                    .getSimpleName(),
                            violation.getPropertyPath().toString(), violation.getMessage());
            errorList.add(errorDetails);
        }

        errorLogger.logErrors(errorList);
    }

    private void logErrorshand(String fileName, int recordNumber, String errorCode,
            String errorClassificationName, String errorDescription) {
        Map<String, Object> errorDetails = errorLogger.createErrorDetails(fileName, recordNumber,
                errorCode, errorClassificationName, errorDescription);
        List<Map<String, Object>> errorList = new ArrayList<>();
        errorList.add(errorDetails);
        errorLogger.logErrors(errorList);
    }
}
