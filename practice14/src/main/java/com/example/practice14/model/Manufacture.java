package com.example.practice14.model;

public class Manufacture {
    private String name;
    private String address;

    public Manufacture(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Manufacture{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
