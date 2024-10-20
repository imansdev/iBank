package com.ibank.bankingprocess.process;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CsvProcess {
    @Value("${csv.file.account}")
    private String accountCsv;
    @Value("${csv.file.customer}")
    private String CustomerCsv;
    @Autowired
    private AccountsProcess accountsProcess;
    @Autowired
    private CustomersProcess customersProcess;

    @Scheduled(cron = "0 */15 * * * ?")
    public void readAndProcessFiles() throws Exception {
        try {
            CompletableFuture<Void> customerFuture = customersProcess.processFile(CustomerCsv);


            customerFuture.get();

            CompletableFuture<Void> accountFuture = accountsProcess.processFile(accountCsv);

            accountFuture.get();

            // CompletableFuture<Void> allFilesProcessed = CompletableFuture.allOf(customerFuture);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error processing files: " + e.getMessage());
        }
    }
}
