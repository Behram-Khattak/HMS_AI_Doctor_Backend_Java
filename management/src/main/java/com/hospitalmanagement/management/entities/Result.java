package com.hospitalmanagement.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @OneToOne
    AppointmentTests appointmentTests;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AppointmentTests getAppointmentTests() {
        return appointmentTests;
    }

    public void setAppointmentTests(AppointmentTests appointmentTests) {
        this.appointmentTests = appointmentTests;
    }
}
