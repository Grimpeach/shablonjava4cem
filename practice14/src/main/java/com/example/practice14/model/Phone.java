package com.example.practice14.model;

public class Phone {
    private String name;
    private int creationYear;

    public Phone(String name, int creationYear) {
        this.name = name;
        this.creationYear = creationYear;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(int creationYear) {
        this.creationYear = creationYear;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "name='" + name + '\'' +
                ", creationYear=" + creationYear +
                '}';
    }
}
