package com.hospitalmanagement.management.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionModel {
    Long id;
    List<PrescriptionMedicinesModel> prescriptionMedicines;
    String status;
    double amount;
    LocalDate date;
}
