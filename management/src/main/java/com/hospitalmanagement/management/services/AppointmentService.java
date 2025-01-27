package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.*;
import com.hospitalmanagement.management.repositories.AppointmentRepository;
import com.hospitalmanagement.management.repositories.PatientRepository;
import com.hospitalmanagement.management.repositories.TransactionRepository;
import com.hospitalmanagement.management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    UserRepository usersRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private PatientRepository patientRepository;

    public Long generateToken(Long doctorId, LocalDate date) {
        // Get the last token for the doctor on the given date
        Long lastToken = appointmentRepository.getToken(doctorId, date);

        // If there are no appointments for the doctor on the given date, start from 1
        if (lastToken == null) {
            return Long.valueOf(1);
        }

        // Otherwise, increment the last token
        return lastToken + 1;
    }

    public Appointment saveAppointment(Appointment appointment) {
        Long doctorId = appointment.getDoctor().getId();
        LocalDate date = appointment.getDate();
        Users doctor=usersRepository.findById(doctorId) .orElseThrow(() -> new RuntimeException("No such user found"));
        appointment.setDoctor(doctor);
        appointment.setPatient(patientRepository.findById(appointment.getPatient().getId()).get());
        // Generate and set the token
        Long token = generateToken(doctorId, date);
        appointment.setToken(token);
        appointment.setStatus("scheduled");
        appointment= appointmentRepository.save(appointment);
        Transaction transaction=new Transaction();
        transaction.setAmount(appointment.getCharges());
        transaction.setCreatedAt(appointment.getDate());
        transaction.setRecordId(appointment.getId());
        transaction.setStatus("valid");
        transaction.setTransactionOf("appointment");
        transaction.setPatient(appointment.getPatient());
        transactionRepository.save(transaction);
        return appointment;
    }

    public Appointment getAppointment(Long id)
    {
        return appointmentRepository.findById(id) .orElseThrow(() -> new RuntimeException("No such appointment found"));
    }

    public List<Appointment> getAppointmentByDoctorAndDate(Long doctorId,LocalDate date)
    {

        return appointmentRepository.findAllByDoctorIdAndDate(doctorId, date);
    }
    public Appointment changeStatus(String status,Long id)
    {
        Optional<Appointment> optionalAppointment=appointmentRepository.findById(id);
        if (optionalAppointment.isPresent()) {
            Appointment appointment=optionalAppointment.get();
            appointment.setStatus(status);
            if(status.equalsIgnoreCase("cancelled"))
            {
                Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(appointment.getId(),"appointment");
                transactionRepository.deleteById(transaction.getId());
            } else if (status.toLowerCase().contains("refund")) {
                Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(appointment.getId(),"appointment");
                if(transaction!=null)
                {
                    transaction.setStatus(status);
                    transactionRepository.save(transaction);
                }
                else{
                    throw new RuntimeException("Transaction not found for given record Id");
                }
            }
            return appointmentRepository.save(appointment);
        }
        else
        {
            throw new RuntimeException("Appointment not found");
        }
    }

    public List<Appointment> getAllAppointments()
    {
        return appointmentRepository.findAll();
    }

    public Appointment addMedicalCondition(Long appointmentId, MedicalCondition medicalCondition)
    {
        Optional<Appointment> optionalAppointment=appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent())
        {
            Appointment appointment=optionalAppointment.get();
            appointment.setMedicalCondition(medicalCondition);
            return appointmentRepository.save(appointment);
        }
        else
        {
            throw new RuntimeException("appointment not found");
        }
    }

    public Appointment updateMedicalCondition(Long appointmentId,MedicalCondition medicalCondition)
    {
        Optional<Appointment> optionalAppointment=appointmentRepository.findById(appointmentId);
        if(optionalAppointment.isPresent())
        {
            Appointment appointment=optionalAppointment.get();
            medicalCondition.setId(appointment.getMedicalCondition().getId());
            appointment.setMedicalCondition(medicalCondition);
            return appointmentRepository.save(appointment);
        }
        else
        {
            throw new RuntimeException("appointment not found");
        }
    }


    public MedicalCondition getMedicalCondition(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get().getMedicalCondition();
    }

    public Appointment addTest(Test test, Long appointmentId) {
        Appointment appointment=appointmentRepository.findById(appointmentId) .orElseThrow(() -> new RuntimeException("No such appointment found"));
        List<AppointmentTests> tests=appointment.getAppointmentTests();
        AppointmentTests appointmentTest=new AppointmentTests();
        appointmentTest.setTest(test);
        appointmentTest.setAppointment(appointment);
        tests.add(appointmentTest);
        appointment.setAppointmentTests(tests);
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsOfPatient(Long patientId) {
        return appointmentRepository.findAllByPatientId(patientId);
    }

    public List<Appointment> getAllAppointmentsByDate(LocalDate date) {
        return appointmentRepository.findAllByDate(date);
    }
}
