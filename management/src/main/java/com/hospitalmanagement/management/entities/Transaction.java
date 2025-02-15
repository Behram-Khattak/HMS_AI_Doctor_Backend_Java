package com.hospitalmanagement.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    Patient patient;
    String transactionOf;
    Long recordId;
    double amount;
    String status;
    LocalDate createdAt;

    // Getters
    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public String getTransactionOf() {
        return transactionOf;
    }

    public Long getRecordId() {
        return recordId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setTransactionOf(String transactionOf) {
        this.transactionOf = transactionOf;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
}
