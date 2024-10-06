package com.ibank.bankingprocess.process.error;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ErrorLogger {

    @Value("${json.file.erro}")
    private String ERROR_FILE;

    private synchronized void writeErrorsToFile() {

    }
}
