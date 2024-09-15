package com.example.printer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfig {

    @Bean(name = "consolePrinter")
    public Printer consolePrinter() {
        return new ConsolePrinter();
    }

    @Bean(name = "filePrinter")
    public Printer filePrinter() {
        return new FilePrinter();
    }
}
