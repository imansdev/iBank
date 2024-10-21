package com.ibank.bankingprocess.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FileWriterFactory {

    @Autowired
    @Qualifier("jsonFileWriter")
    private FileWriterStrategy jsonFileWriter;

    @Autowired
    @Qualifier("xmlFileWriter")
    private FileWriterStrategy xmlFileWriter;

    // Factory method to get the appropriate file writer based on the format
    public FileWriterStrategy getFileWriter(String format) {
        return format.equalsIgnoreCase("json") ? jsonFileWriter : xmlFileWriter;
    }
}
