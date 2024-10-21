package com.ibank.bankingprocess.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ibank.bankingprocess.dto.CustomerAccountOutDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class XmlFileWriter implements FileWriterStrategy {

    @Value("${file.output.xml.path}")
    private String FilePath;

    @Override
    public void writeToFile(List<CustomerAccountOutDTO> accounts) throws IOException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FilePath), accounts);
    }
}
