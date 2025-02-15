package com.hospitalmanagement.management.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "appointment_tests")
@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AppointmentTests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "test_id")
    Test test;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    Appointment appointment;

    String status;
    LocalDate date;

    // Getters
    public Long getId() {
        return id;
    }

    public Test getTest() {
        return test;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public String getStatus() {
        return status;
    }

    public LocalDate getDate() {
        return date;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
