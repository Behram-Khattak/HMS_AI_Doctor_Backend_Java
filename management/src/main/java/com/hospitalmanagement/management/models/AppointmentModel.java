package com.hospitalmanagement.management.models;

import com.hospitalmanagement.management.entities.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentModel {

    Long id;
    Users doctor;
    Patient patient;
    LocalDate date;
    String department;

    Long token;

    String status;

    double charges;

    boolean diagnosis;

    PrescriptionModel prescription;

    MedicalCondition medicalCondition;

    List<AppointmentTestsModel> appointmentTests;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getDoctor() {
        return doctor;
    }

    public void setDoctor(Users doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCharges() {
        return charges;
    }

    public void setCharges(double charges) {
        this.charges = charges;
    }

    public boolean isDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(boolean diagnosis) {
        this.diagnosis = diagnosis;
    }

    public PrescriptionModel getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionModel prescription) {
        this.prescription = prescription;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public List<AppointmentTestsModel> getAppointmentTests() {
        return appointmentTests;
    }

    public void setAppointmentTests(List<AppointmentTestsModel> appointmentTests) {
        this.appointmentTests = appointmentTests;
    }

    // Getters and Setters
}
