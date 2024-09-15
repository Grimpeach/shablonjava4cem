package com.example.practice12;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class Practice12Application {
    private BufferedWriter writer;
    private BufferedReader reader;
    private Path inputFilePath;
    private Path outputFilePath;

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Please provide the input and output file names as arguments.");
            System.exit(1);
        }
        SpringApplication.run(Practice12Application.class, args);
    }

    @PostConstruct
    public void init() {
        try {
            inputFilePath = Paths.get(System.getProperty("input.file", "input.txt"));
            outputFilePath = Paths.get(System.getProperty("output.file", "output.txt"));

            if (!Files.exists(inputFilePath)) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {
                    writer.write("null");
                    System.out.println("Input file not found. Created output file with 'null'.");
                }
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath.toFile()));
                     BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath.toFile()))) {
                    int character;
                    while ((character = reader.read()) != -1) {
                        writer.write(character);
                    }
                    System.out.println("File successfully copied to output.");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void destroy() {
        try {
            if (Files.exists(inputFilePath)) {
                Files.delete(inputFilePath);
                System.out.println("Input file deleted.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
