package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Patient;
import com.hospitalmanagement.management.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/patient")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable String id)
    {
        return patientService.getPatient(id);
    }
    @GetMapping
    public List<Patient> getAllPatients()
    {
        return patientService.getAllPatients();
    }
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient)
    {
        return patientService.addPatient(patient);
    }
    @PutMapping("/{id}")
    public Patient editPatient(@RequestBody Patient patient,@PathVariable String id)
    {
        return patientService.editPatient(patient,id);
    }
}
