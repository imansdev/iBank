package com.ibank.bankingprocess.aspect;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // Log the start and end of file processing for AccountProcess and CustomerProcess
    @Around("execution(* com.ibank.bankingprocess.process.*Process.processFile(..))")
    public Object logFileProcessing(ProceedingJoinPoint joinPoint) throws Throwable {
        String fileName = (String) joinPoint.getArgs()[0];
        logger.info("Start processing file: " + fileName);
        try {
            Object result = joinPoint.proceed();
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

    // Log when any method in the controller layer is called
    @Before("execution(* com.ibank.bankingprocess.controller..*(..))")
    public void logControllerMethod(JoinPoint joinPoint) {
        logger.info("Entering: {} with arguments: {}", joinPoint.getSignature(),
                joinPoint.getArgs());
    }

    // Log when any method in the service layer is called
    @Before("execution(* com.ibank.bankingprocess.service..*(..))")
    public void logServiceMethod(JoinPoint joinPoint) {
        logger.debug("Service method called: {}", joinPoint.getSignature());
    }

    // Log the result of the method in the controller layer after successful execution
    @AfterReturning(pointcut = "execution(* com.ibank.bankingprocess.controller..*(..))",
            returning = "result")
    public void logControllerMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Exiting controller method: " + methodName);
    }

    // Log the result of the method in the service layer after successful execution
    @AfterReturning(pointcut = "execution(* com.ibank.bankingprocess.service..*(..))",
            returning = "result")
    public void logServiceMethodReturn(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        logger.info("Exiting service method: " + methodName);
    }

}
