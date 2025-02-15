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

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<PrescriptionMedicinesModel> getPrescriptionMedicines() {
        return prescriptionMedicines;
    }

    public void setPrescriptionMedicines(List<PrescriptionMedicinesModel> prescriptionMedicines) {
        this.prescriptionMedicines = prescriptionMedicines;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getters and Setters
}
