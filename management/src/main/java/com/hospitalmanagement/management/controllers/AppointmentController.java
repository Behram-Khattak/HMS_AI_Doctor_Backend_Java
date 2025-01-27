package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Appointment;
import com.hospitalmanagement.management.entities.MedicalCondition;
import com.hospitalmanagement.management.entities.Test;
import com.hospitalmanagement.management.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {

        return appointmentService.saveAppointment(appointment);
    }

    @GetMapping
    public List<Appointment> getAllAppointments()
    {
        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id)
    {
        return appointmentService.getAppointment(id);
    }

    @GetMapping("/get-by-date/{date}")
    public List<Appointment> getAllAppointmentsByDate(@PathVariable String date){

        return appointmentService.getAllAppointmentsByDate(LocalDate.parse(date));
    }

    @PutMapping("/change-status/{id}/{status}")
    public Appointment changeStatus(@PathVariable Long id,@PathVariable String status)
    {
        return appointmentService.changeStatus(status,id);
    }

    @GetMapping("/{doctorId}/{date}")
    public List<Appointment> getAppointmentByDoctorAndDate(@PathVariable Long doctorId,@PathVariable LocalDate date)
    {
        return appointmentService.getAppointmentByDoctorAndDate(doctorId,date);
    }

    @PostMapping("/add-medical-condition/{appointmentId}")
    public Appointment addMedicalCondition(@PathVariable Long appointmentId,@RequestBody MedicalCondition medicalCondition)
    {
        return appointmentService.addMedicalCondition(appointmentId,medicalCondition);
    }

    @PutMapping("/update-medical-condition/{appointmentId}")
    public Appointment editMedicalCondition(@PathVariable Long appointmentId,@RequestBody MedicalCondition medicalCondition)
    {
        return appointmentService.updateMedicalCondition(appointmentId,medicalCondition);
    }

    @GetMapping("/medical-condition/get/{appointmentId}")
    public MedicalCondition getMedicalCondition(@PathVariable Long appointmentId)
    {
        return appointmentService.getMedicalCondition(appointmentId);
    }

    @GetMapping("/get-all-appointments-of-patient/{patientId}")
    public List<Appointment> getAllAppointmentsOfPatient(@PathVariable Long patientId){
        return appointmentService.getAllAppointmentsOfPatient(patientId);
    }
}
