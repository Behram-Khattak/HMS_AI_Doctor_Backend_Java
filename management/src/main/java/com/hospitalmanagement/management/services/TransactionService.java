package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.*;
import com.hospitalmanagement.management.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentTestsRepository appointmentTestsRepository;

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    PrescriptionMedicinesRepository prescriptionMedicinesRepository;

    @Autowired
    MedicineRepository medicineRepository;

    public List<Transaction> getTransactionsByDate(LocalDate date)
    {
        return transactionRepository.findAllByCreatedAt(date);
    }



    public Transaction changeStatus(Long id, String status) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(id);

        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setStatus(status);
            if(status.contains("paid") || status.contains("Paid"))
            {
                if(transaction.getTransactionOf().contains("appointment"))
                {
                    Appointment appointment=appointmentRepository.findById(transaction.getRecordId()) .orElseThrow(() -> new RuntimeException("No such appointment found"));
                    appointment.setStatus("in-progress");
                    appointmentRepository.save(appointment);
                }
                if(transaction.getTransactionOf().contains("medicine"))
                {
                    Prescription prescription=prescriptionRepository.findById(transaction.getRecordId()).orElseThrow(()->new RuntimeException("No such prescription found"));
                    prescription.setStatus("in-progress");
                    prescriptionRepository.save(prescription);
                    for(PrescriptionMedicines prescriptionMedicine: prescription.getPrescriptionMedicines()) {

                    Medicine medicine=prescriptionMedicine.getMedicine();
                    medicine.setStock(medicine.getStock()-prescriptionMedicine.getQuantity());
                    medicine=medicineRepository.save(medicine);
                    prescriptionMedicine.setMedicine(medicine);
                    prescriptionMedicinesRepository.save(prescriptionMedicine);
                    }
                }
                if(transaction.getTransactionOf().contains("test"))
                {
                    AppointmentTests appointmentTests=appointmentTestsRepository.findById(transaction.getRecordId()) .orElseThrow(() -> new RuntimeException("No such appointment test found"));
                    appointmentTests.setStatus("in-progress");
                    appointmentTestsRepository.save(appointmentTests);
                }
            }
            return transactionRepository.save(transaction);
        } else {
            throw new RuntimeException("Transaction with ID " + id + " not found");
        }
    }

}
