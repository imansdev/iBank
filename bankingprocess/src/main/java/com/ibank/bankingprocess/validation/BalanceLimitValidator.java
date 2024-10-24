package com.ibank.bankingprocess.validation;

import org.springframework.beans.factory.annotation.Value;
import com.ibank.bankingprocess.dto.AccountInputDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BalanceLimitValidator
        implements ConstraintValidator<ValidBalanceLimit, AccountInputDTO> {

    @Value("${account.limit.savings}")
    Long limitSavings;
    @Value("${account.limit.RD}")
    Long limitRD;
    @Value("${account.limit.FD}")
    Long limitFD;

    @Override
    public boolean isValid(AccountInputDTO account, ConstraintValidatorContext context) {
        Long balanceLimit;

        Long accountBalanceLimit = account.getAccountBalanceLimit();

        switch (account.getAccountType()) {
            case "SAVINGS":
                balanceLimit = limitSavings;
                break;
            case "RD":
                balanceLimit = limitRD;
                break;
            case "FD":
                balanceLimit = limitFD;
                break;
            default:
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(
                        "Invalid account type. Allowed values are SAVINGS, RD, FD. " + " not be "
                                + account.getAccountType())
                        .addPropertyNode("accountType").addConstraintViolation();
                return false;
        }

        if (!balanceLimit.equals(accountBalanceLimit)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Account balance limit for account type "
                    + account.getAccountType() + " should be " + balanceLimit)
                    .addPropertyNode("accountBalanceLimit").addConstraintViolation();
            return false;
        }

        if (Long.parseLong(account.getBalance()) > balanceLimit) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Balance exceeds the allowed limit for account type " + account.getAccountType()
                            + ". Max allowed: " + balanceLimit)
                    .addPropertyNode("balance").addConstraintViolation();
            return false;
        }

        return true;
    }
}
