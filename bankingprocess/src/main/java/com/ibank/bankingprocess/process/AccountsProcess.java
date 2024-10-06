package com.ibank.bankingprocess.process;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
public class AccountsProcess {
    @Async
    public void processFile(String accountCsv) {

        try (Stream<String> lines = Files.lines(Paths.get(accountCsv))) {
            lines.skip(1).parallel().forEach(line -> this.processLine(line, accountCsv));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processLine(String line, String fileName) {

    }

}
