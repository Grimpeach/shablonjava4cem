package com.example.printer;

import org.springframework.stereotype.Component;

@Component("consolePrinter")
public class ConsolePrinter implements Printer {
    @Override
    public void doPrint() {
        System.out.println("Printing to console");
    }
}
