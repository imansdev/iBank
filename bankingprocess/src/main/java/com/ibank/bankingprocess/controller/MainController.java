package com.ibank.bankingprocess.controller;

import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import com.ibank.bankingprocess.service.AccountService;
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
    private AccountService accountService;

    // Endpoint to export data in JSON format
    // @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ResponseEntity<List<CustomerAccountOutDTO>> exportAccountAsJson() {
    // List<CustomerAccountOutDTO> account =
    // accountService.getAccountWithBalanceGreaterThan1000();
    // return ResponseEntity.ok(account);
    // }

    // // Endpoint to export data in XML format
    // @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    // public ResponseEntity<List<CustomerAccountOutDTO>> exportAccountAsXml() {
    // List<CustomerAccountOutDTO> account =
    // accountService.getAccountWithBalanceGreaterThan1000();
    // return ResponseEntity.ok(account);
    // }
}
