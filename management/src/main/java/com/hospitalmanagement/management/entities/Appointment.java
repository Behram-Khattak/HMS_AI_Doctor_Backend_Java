package com.hospitalmanagement.management.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    Users doctor;

    @ManyToOne
    Patient patient;

    LocalDate date;

    String department;

    Long token;

    String status;

    double charges;

    boolean diagnosis;

    @OneToOne
    Prescription prescription;

    @ManyToOne(cascade = CascadeType.ALL)
    MedicalCondition medicalCondition;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appointment", cascade = CascadeType.ALL)
    List<AppointmentTests> appointmentTests;

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

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public MedicalCondition getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public List<AppointmentTests> getAppointmentTests() {
        return appointmentTests;
    }

    public void setAppointmentTests(List<AppointmentTests> appointmentTests) {
        this.appointmentTests = appointmentTests;
    }

}
