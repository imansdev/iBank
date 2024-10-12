package com.ibank.bankingprocess.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.ibank.bankingprocess.repository.CustomersRepository;

public class CustomerIdValidator implements ConstraintValidator<ValidCustomerId, Long> {

    @Autowired
    private CustomersRepository customersRepository;

    @Override
    public boolean isValid(Long customerId, ConstraintValidatorContext context) {
        if (customerId == null) {
            return false;
        }

        boolean customerExists = customersRepository.existsByCustomerId(customerId);

        if (!customerExists) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Customer not found with customerId: " + customerId)
                    .addPropertyNode("customerId").addConstraintViolation();

            return false;
        }

        return true;
    }
}
