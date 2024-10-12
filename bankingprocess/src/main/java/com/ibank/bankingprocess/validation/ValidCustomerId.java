package com.ibank.bankingprocess.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomerIdValidator.class)
public @interface ValidCustomerId {
    String message() default "Customer not found";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
