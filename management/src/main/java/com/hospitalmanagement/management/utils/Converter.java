package com.hospitalmanagement.management.utils;

import com.hospitalmanagement.management.entities.Appointment;
import com.hospitalmanagement.management.entities.AppointmentTests;
import com.hospitalmanagement.management.entities.PrescriptionMedicines;
import com.hospitalmanagement.management.entities.Test;
import com.hospitalmanagement.management.models.*;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Converter {

    private static final Logger logger = LoggerFactory.getLogger(Converter.class);

    public static AppointmentModel convertAppointmentToAppointmentModel(Appointment appointment) {
        AppointmentModel appointmentModel = new AppointmentModel();
        appointmentModel.setId(appointment.getId());
        appointmentModel.setDate(appointment.getDate());
        appointmentModel.setCharges(appointment.getCharges());
        appointmentModel.setDiagnosis(appointment.isDiagnosis());
        appointmentModel.setDepartment(appointment.getDepartment());
        appointmentModel.setDoctor(appointment.getDoctor());
        appointmentModel.setPatient(appointment.getPatient());
        appointmentModel.setToken(appointment.getToken());
        appointmentModel.setMedicalCondition(appointment.getMedicalCondition());
        appointmentModel.setStatus(appointment.getStatus());
        List<AppointmentTestsModel> appointmentTests = new ArrayList<>();
        for (AppointmentTests test : appointment.getAppointmentTests()) {
            AppointmentTestsModel appointmentTestsModel = new AppointmentTestsModel();
            appointmentTestsModel.setId(test.getId());
            appointmentTestsModel.setTest(covertTestToTestModel(test.getTest()));
            appointmentTestsModel.setStatus(test.getStatus());
            // appointmentTestsModel.setAppointment(test.getAppointment());
            appointmentTests.add(appointmentTestsModel);
        }
        appointmentModel.setAppointmentTests(appointmentTests);
        PrescriptionModel prescriptionModel = new PrescriptionModel();
        List<PrescriptionMedicinesModel> prescriptionMedicinesModels = new ArrayList<>();
        if (appointment.getPrescription() != null) {
            for (PrescriptionMedicines medicine : appointment.getPrescription().getPrescriptionMedicines()) {
                PrescriptionMedicinesModel prescriptionMedicinesModel = new PrescriptionMedicinesModel();
                prescriptionMedicinesModel.setId(medicine.getId());
                prescriptionMedicinesModel.setMedicine(medicine.getMedicine());
                prescriptionMedicinesModel.setQuantity(medicine.getQuantity());
                prescriptionMedicinesModel.setPrice(medicine.getPrice());

                prescriptionMedicinesModels.add(prescriptionMedicinesModel);
            }

            prescriptionModel.setPrescriptionMedicines(prescriptionMedicinesModels);
            prescriptionModel.setId(appointment.getPrescription().getId());
            prescriptionModel.setDate(appointment.getPrescription().getDate());
            prescriptionModel.setStatus(appointment.getPrescription().getStatus());
            prescriptionModel.setAmount(appointment.getPrescription().getAmount());
            prescriptionModel.setStatus(appointment.getPrescription().getStatus());
        }
        appointmentModel.setPrescription(prescriptionModel);
        return appointmentModel;

    }

    public static AppointmentTestsModel convertAppointmentTestToAppointmentTestModel(AppointmentTests appointmentTest) {
        AppointmentTestsModel appointmentTestsModel = new AppointmentTestsModel();
        appointmentTestsModel.setAppointment(convertAppointmentToAppointmentModel(appointmentTest.getAppointment()));
        appointmentTestsModel.setStatus(appointmentTest.getStatus());
        appointmentTestsModel.setTest(covertTestToTestModel(appointmentTest.getTest()));
        appointmentTestsModel.setId(appointmentTest.getId());

        return appointmentTestsModel;
    }

    // public static TestModel covertTestToTestModel(Test test)
    // {
    // return new TestModel(test.getId(), test.getName(), test.getDescription(),
    // test.getPrice(), test.getDepartment() );

    // }

    public static TestModel covertTestToTestModel(Test test) {
        if (test == null) {
            logger.warn("Test object is null.");
            return null;
        }

        if (test.getName() == null || test.getName().isEmpty()) {
            logger.warn("Test name is null or empty.");
            return null;
        }

        if (test.getDescription() == null || test.getDescription().isEmpty()) {
            logger.warn("Test description is null or empty.");
            return null;
        }

        if (test.getDepartment() == null || test.getDepartment().isEmpty()) {
            logger.warn("Test department is null or empty.");
            return null;
        }

        if (test.getPrice() <= 0) {
            logger.warn("Test price is invalid or zero.");
            return null;
        }

        return new TestModel(test.getId(), test.getName(), test.getDescription(), test.getPrice(),
                test.getDepartment());
    }
}
