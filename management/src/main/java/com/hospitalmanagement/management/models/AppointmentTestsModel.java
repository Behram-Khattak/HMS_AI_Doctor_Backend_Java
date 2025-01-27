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
}
