package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.Patient;
import com.hospitalmanagement.management.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    PatientRepository patientRepository;
    public Patient getPatient(String id){
        if(patientRepository.findById(id).isPresent()) {
            return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("No such patient found"));
        }
        else {
            return new Patient();
        }
    }
    public List<Patient> getAllPatients()
    {
        return patientRepository.findAll();
    }
    public Patient addPatient(Patient patient)
    {
        return patientRepository.save(patient);
    }
    public Patient editPatient(Patient patient,String id)
    {
        Patient fetchedPatient=patientRepository.findById(id) .orElseThrow(() -> new RuntimeException("No such patient found"));
        if(fetchedPatient!=null)
        {
            fetchedPatient.setAddress(patient.getAddress());
            fetchedPatient.setDob(patient.getDob());
            fetchedPatient.setGender(patient.getGender());
            fetchedPatient.setFullName(patient.getFullName());
            fetchedPatient.setPhoneNumber(patient.getPhoneNumber());
            fetchedPatient.setEmailAddress(patient.getEmailAddress());
            return patientRepository.save(fetchedPatient);
        }
        else {
            throw new RuntimeException("No patient found for the given Id");
        }
    }
}
