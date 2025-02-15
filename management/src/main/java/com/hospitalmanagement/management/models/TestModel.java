package com.hospitalmanagement.management.models;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
public class TestModel {
    private Long id;
    private String name;
    private String description;
    private double price;
    private String department;

    public TestModel(Long id, String name, String description, double price, String department) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.department = department;
    }

}