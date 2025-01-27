package com.hospitalmanagement.management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TestModel {
    Long id;
    String name;
    String description;
    double price;
    String department;
}
