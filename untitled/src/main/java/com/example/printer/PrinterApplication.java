package com.example.printer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrinterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrinterApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext ctx) {
        return args -> {
            if (args.length > 0) {
                String beanName = args[0];
                Printer printer = (Printer) ctx.getBean(beanName);
                printer.doPrint();
            } else {
                System.out.println("Please provide a bean name as an argument.");
            }
        };
    }
}
