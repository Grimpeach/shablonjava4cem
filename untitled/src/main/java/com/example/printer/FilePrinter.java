package com.example.printer;

import org.springframework.stereotype.Component;

@Component("filePrinter")
public class FilePrinter implements Printer {
    @Override
    public void doPrint() {
        System.out.println("Printing to file");
    }
}
