package com.ibank.bankingprocess.service;

import java.io.IOException;
import java.util.List;
import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;

public interface FileWriterStrategy {
    void writeToFile(List<CustomerAccountOutDTO> accounts) throws IOException;
}
