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
import com.ibank.bankingprocess.dto.CustomerInputDTO;
import com.ibank.bankingprocess.error.ErrorLogger;
import com.ibank.bankingprocess.service.CustomerService;
import com.ibank.bankingprocess.utils.EncryptionUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Component
public class CustomerProcess {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private Validator validator;

    @Autowired
    private ErrorLogger errorLogger;


    @Async
    public CompletableFuture<Void> processFile(String customerCsv) {

        try (Stream<String> lines = Files.lines(Paths.get(customerCsv))) {
            lines.skip(1).parallel().forEach(line -> this.processLine(line, customerCsv));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(null);
    }

    public void processLine(String line, String fileName) {
        String[] fields = line.split(",");

        try {
            int recordNumber = Integer.parseInt(fields[0]);
            if (fields.length != 9
                    || Arrays.stream(fields).anyMatch(field -> field.trim().isEmpty())) {
                logErrorshand(fileName, recordNumber, "NULL_FIELD", "Field",
                        "One or more fields are null or contain only spaces.");
                return;
            }

            String name = fields[1];
            String surname = fields[2];
            String nationalId = fields[3];
            LocalDate dateOfBirth = LocalDate.parse(fields[4]);
            String street = fields[5];
            String city = fields[6];
            Long zipCode = Long.parseLong(fields[7]);
            Long customerId = Long.parseLong(fields[8]);

            CustomerInputDTO customer = new CustomerInputDTO();
            customer.setName(EncryptionUtil.decrypt(name));
            customer.setSurname(EncryptionUtil.decrypt(surname));
            customer.setNationalId(EncryptionUtil.decrypt(nationalId));
            customer.setDateOfBirth(dateOfBirth);
            customer.setStreet(street);
            customer.setCity(city);
            customer.setZipCode(zipCode);
            customer.setCustomerId(customerId);

            Set<ConstraintViolation<CustomerInputDTO>> violations = validator.validate(customer);

            if (!violations.isEmpty()) {
                logErrors(violations, fileName, recordNumber);
            } else {
                customer.setNationalId(EncryptionUtil.encrypt(nationalId));
                customer.setName(EncryptionUtil.encrypt(name));
                customer.setSurname(EncryptionUtil.encrypt(surname));
                customerService.customerInsertion(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void logErrors(Set<ConstraintViolation<CustomerInputDTO>> violations, String fileName,
            int recordNumber) {
        List<Map<String, Object>> errorList = new ArrayList<>();
        for (ConstraintViolation<CustomerInputDTO> violation : violations) {
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
