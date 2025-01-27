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

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "appointment", cascade = CascadeType.ALL)
    List<AppointmentTests> appointmentTests;

}
