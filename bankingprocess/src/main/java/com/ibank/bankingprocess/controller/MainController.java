package com.ibank.bankingprocess.controller;

import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import com.ibank.bankingprocess.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/accountlist")
public class MainController {

    @Autowired
    private AccountsService accountsService;

    // Endpoint to export data in JSON format
    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerAccountOutDTO>> exportAccountsAsJson() {
        List<CustomerAccountOutDTO> accounts =
                accountsService.getAccountsWithBalanceGreaterThan1000();
        return ResponseEntity.ok(accounts);
    }

    // Endpoint to export data in XML format
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<CustomerAccountOutDTO>> exportAccountsAsXml() {
        List<CustomerAccountOutDTO> accounts =
                accountsService.getAccountsWithBalanceGreaterThan1000();
        return ResponseEntity.ok(accounts);
    }
}
