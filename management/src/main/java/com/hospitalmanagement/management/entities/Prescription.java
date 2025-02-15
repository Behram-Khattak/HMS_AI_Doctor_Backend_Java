package com.hospitalmanagement.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "prescription", cascade = CascadeType.ALL)
    private List<PrescriptionMedicines> prescriptionMedicines;

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

    public List<PrescriptionMedicines> getPrescriptionMedicines() {
        return prescriptionMedicines;
    }

    public void setPrescriptionMedicines(List<PrescriptionMedicines> prescriptionMedicines) {
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
}
