package com.ibank.bankingprocess.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import com.ibank.bankingprocess.service.AccountService;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    @Autowired
    private AccountService accountService;

    @GetMapping(value = "/accountlist")
    public ResponseEntity<List<CustomerAccountOutDTO>> getCustomersWithBalance(
            @RequestParam(value = "format", defaultValue = "json") String format)
            throws IOException {

        List<CustomerAccountOutDTO> result;
        MediaType contentType;

        contentType = format.equalsIgnoreCase("json") ? MediaType.APPLICATION_JSON
                : format.equalsIgnoreCase("xml") ? MediaType.APPLICATION_XML : null;

        if (contentType == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        result = accountService.saveToFile(format);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, contentType.toString())
                .body(result);
    }
}
