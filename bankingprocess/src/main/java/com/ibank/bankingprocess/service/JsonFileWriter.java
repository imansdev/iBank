package com.ibank.bankingprocess.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JsonFileWriter implements FileWriterStrategy {

    @Value("${file.output.json.path}")
    private String FilePath;

    @Override
    public void writeToFile(List<CustomerAccountOutDTO> accounts) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FilePath), accounts);
    }
}
