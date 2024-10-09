package com.ibank.bankingprocess.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;

// Implement the validation logic
public class AgeValidator implements ConstraintValidator<ValidAge, LocalDate> {

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {

        // Calculate the age
        int age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        // Check if the age is >= 18
        if (age >= 18) {
            return true;
        } else {
            // Disable default message and set custom one at the "dateOfBirth" node
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("The age of the customer is under 18")
                    .addPropertyNode("dateOfBirth").addConstraintViolation();
            return false;
        }
    }
}
