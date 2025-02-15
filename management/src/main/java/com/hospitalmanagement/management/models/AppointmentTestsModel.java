package com.hospitalmanagement.management.models;

import com.hospitalmanagement.management.entities.Appointment;
import com.hospitalmanagement.management.entities.Test;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AppointmentTestsModel {
    Long id;
    TestModel test;
    String status;
    AppointmentModel appointment;

    public Long getId() {
        return id;
    }

    public TestModel getTest() {
        return test;
    }

    public String getStatus() {
        return status;
    }

    public AppointmentModel getAppointment() {
        return appointment;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTest(TestModel test) {
        this.test = test;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setAppointment(AppointmentModel appointment) {
        this.appointment = appointment;
    }

    // Getters and Setters
}
