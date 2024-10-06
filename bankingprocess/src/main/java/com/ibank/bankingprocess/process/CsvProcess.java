package com.ibank.bankingprocess.process;

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

    @Scheduled(cron = "0 */1 * * * ?")
    public void readAndProcessFiles() throws Exception {
        customersProcess.processFile(CustomerCsv);

    }
}
