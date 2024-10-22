package com.ibank.bankingprocess.error;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class ErrorLogger {

    @Value("${json.file.erro}")
    private String ERROR_FILE;

    // Pretty printer for JSON
    private final ObjectWriter jsonWriter = Jackson2ObjectMapperBuilder.json()
            .simpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").build().writerWithDefaultPrettyPrinter();

    private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

    // Log errors to error.json
    public void logErrors(List<Map<String, Object>> errorList) {
        writeErrorsToFile(errorList);
    }

    // Write errors to the error.json file
    private synchronized void writeErrorsToFile(List<Map<String, Object>> errorList) {
        try {
            // Check if the file already exists
            File file = new File(ERROR_FILE);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs(); // Create the directory if it doesn't exist
            }
            List<Map<String, Object>> existingErrors = new ArrayList<>();

            if (file.exists()) {
                // Read existing content and parse it as a list of maps
                String content = new String(Files.readAllBytes(Paths.get(ERROR_FILE)));
                if (!content.isEmpty()) {
                    existingErrors = objectMapper.readValue(content,
                            new TypeReference<List<Map<String, Object>>>() {});
                }
            }

            // Append the new errors to the existing list
            existingErrors.addAll(errorList);

            // Write the updated list back to the file
            try (FileWriter writer = new FileWriter(ERROR_FILE)) {
                writer.write(
                        jsonWriter.writeValueAsString(existingErrors) + System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Helper method to create error details for each violation
    public Map<String, Object> createErrorDetails(String fileName, int recordNumber,
            String errorCode, String errorClassificationName, String errorDescription) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("FILE_NAME", fileName);
        errorDetails.put("RECORD_NUMBER", recordNumber);
        errorDetails.put("ERROR_CODE", errorCode);
        errorDetails.put("ERROR_CLASSIFICATION_NAME", errorClassificationName);
        errorDetails.put("ERROR_DESCRIPTION", errorDescription);
        errorDetails.put("ERROR_DATE", LocalDateTime.now());
        return errorDetails;
    }
}
