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
}
