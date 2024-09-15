package com.marov.practice10_11.practice10;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PrinterTest {
    public static void main(String[] args) {
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("practice10Resources/applicationContext10.xml")) {

            String consolePrinterBean = "consolePrinterBean";
            String filePrinterBean = "filePrinterBean";

            Printer consolePrinter = context.getBean(consolePrinterBean, Printer.class);
            Printer filePrinter = context.getBean(filePrinterBean, Printer.class);

            consolePrinter.doPrint();
            filePrinter.doPrint();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
