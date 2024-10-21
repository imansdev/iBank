package com.ibank.bankingprocess.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.ibank.bankingprocess.repository.CustomerRepository;

public class CustomerIdValidator implements ConstraintValidator<ValidCustomerId, Long> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean isValid(Long customerId, ConstraintValidatorContext context) {
        if (customerId == null) {
            return false;
        }

        boolean customerExists = customerRepository.existsByCustomerId(customerId);

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
