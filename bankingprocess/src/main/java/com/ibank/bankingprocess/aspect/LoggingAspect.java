package com.ibank.bankingprocess.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log the start and end of file processing for AccountsProcess and CustomersProcess
    @Around("execution(* com.ibank.bankingprocess.process.*Process.processFile(..))")
    public Object logFileProcessing(ProceedingJoinPoint joinPoint) throws Throwable {
        String fileName = (String) joinPoint.getArgs()[0]; // Get the file name being processed
        logger.info("Start processing file: " + fileName);
        try {
            Object result = joinPoint.proceed(); // Proceed with the intercepted method
            logger.info("End processing file: " + fileName);
            return result;
        } catch (Exception e) {
            logger.error("Error processing file: " + fileName, e);
            throw e;
        }
    }

    // Log successful database insertions
    @AfterReturning("execution(* com.ibank.bankingprocess.service.*Service.*Insertion(..))")
    public void logDatabaseInsertion() {
        logger.info("Data successfully inserted into the database.");
    }

    // Log when validation errors are written to the error file
    @AfterReturning("execution(* com.ibank.bankingprocess.process.error.ErrorLogger.logErrors(..))")
    public void logErrorFileInsertion() {
        logger.info("Errors have been logged to the error file.");
    }
}
