package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.*;
import com.hospitalmanagement.management.models.AppointmentModel;
import com.hospitalmanagement.management.models.PrescriptionModel;
import com.hospitalmanagement.management.repositories.*;
import com.hospitalmanagement.management.utils.Converter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.LocalDate.now;

@Service
public class PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    PrescriptionMedicinesRepository prescriptionMedicinesRepository;
    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public PrescriptionModel addPrescription(Prescription prescription, Long appointmentId)
    {
        Appointment appointment=appointmentRepository.findById(appointmentId) .orElseThrow(() -> new RuntimeException("No such appointment found"));
        if(appointment!=null)
        {
            prescription.setStatus("scheduled");
            List<PrescriptionMedicines> medicines=prescription.getPrescriptionMedicines();
            prescription.setPrescriptionMedicines(new ArrayList<>());
            prescription=prescriptionRepository.save(prescription);

//            if(prescription.getPrescriptionMedicines()!=null && !prescription.getPrescriptionMedicines().isEmpty())
//            {
//                for(PrescriptionMedicines medicine:prescription.getPrescriptionMedicines())
//                {
//                    Transaction transaction=new Transaction();
//                    transaction.setAmount(medicine.getPrice()* medicine.getQuantity());
//                    transaction.setRecordId(medicine.getId());
//                    transaction.setTransactionOf("medicine");
//                    transaction.setPatient(appointment.getPatient());
//                    transaction.setCreatedAt(appointment.getDate());
//                    transaction.setStatus("valid");
//                    transactionRepository.save(transaction);
//                }
//
//            }
            for(PrescriptionMedicines medicine:medicines)
            {
                addMedicine(appointment,prescription.getId(),medicine);
            }
            prescription=prescriptionRepository.findById(prescription.getId()).orElseThrow(() -> new RuntimeException("No such prescription found"));
            medicines=prescription.getPrescriptionMedicines();
            double totalAmount=0;
            for(PrescriptionMedicines medicine:medicines)
            {
                totalAmount+=medicine.getPrice();
            }
            prescription.setAmount(totalAmount);
            prescription.setDate(appointment.getDate());
            prescription=prescriptionRepository.save(prescription);
            Transaction transaction=new Transaction();
            transaction.setAmount(prescription.getAmount());
            transaction.setRecordId(prescription.getId());
            transaction.setTransactionOf("medicine");
            transaction.setPatient(appointment.getPatient());
            transaction.setCreatedAt(appointment.getDate());
            transaction.setStatus("valid");
            transactionRepository.save(transaction);
            appointment.setPrescription(prescription);
            appointment= appointmentRepository.save(appointment);
            return Converter.convertAppointmentToAppointmentModel(appointment).getPrescription();
        }
        else
        {
            throw new RuntimeException("Appointment not found");
        }
    }

    public Prescription addMedicine(Appointment appointment ,Long prescriptionId, PrescriptionMedicines medicine) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new RuntimeException("No such prescription found"));

//        List<PrescriptionMedicines> medicines = prescriptionRepository.getPrescribedMedicines(prescriptionId);
        List<PrescriptionMedicines> medicines=prescriptionMedicinesRepository.findAll().stream().filter(p-> Objects.equals(p.getPrescription().getId(), prescriptionId)).collect(Collectors.toList());

        if (medicines == null) {
            medicines = new ArrayList<>();
        }

        boolean medicinePresent = false;

        for (PrescriptionMedicines prescriptionMedicine : medicines) {
            if (prescriptionMedicine.getMedicine().getId().equals(medicine.getMedicine().getId())) {
                medicinePresent = true;
                updateExistingMedicine(prescriptionMedicine, medicine);
                break;
            }
        }

        if (!medicinePresent) {
            addNewMedicine(appointment,prescription, medicine, medicines);
        }

        return prescriptionRepository.save(prescription);
    }

    private void updateExistingMedicine(PrescriptionMedicines existingMedicine, PrescriptionMedicines newMedicine) {
        existingMedicine.setQuantity(existingMedicine.getQuantity() + newMedicine.getQuantity());
        existingMedicine.setPrice(existingMedicine.getQuantity() * existingMedicine.getMedicine().getPricePerUnit());

    }

    private void addNewMedicine(Appointment appointment,Prescription prescription, PrescriptionMedicines medicine, List<PrescriptionMedicines> medicines) {
        Medicine fetchedMedicine = medicineRepository.findById(medicine.getMedicine().getId())
                .orElseThrow(() -> new RuntimeException("Medicine not found"));

        PrescriptionMedicines newPrescriptionMedicine = new PrescriptionMedicines();
        newPrescriptionMedicine.setMedicine(fetchedMedicine);
//        newPrescriptionMedicine.setStatus("scheduled");
        newPrescriptionMedicine.setQuantity(medicine.getQuantity());
        newPrescriptionMedicine.setPrice(medicine.getQuantity() * fetchedMedicine.getPricePerUnit());
        newPrescriptionMedicine.setPrescription(prescription);
        newPrescriptionMedicine=prescriptionMedicinesRepository.save(newPrescriptionMedicine);
//        Appointment appointment=appointmentRepository.findByPrescriptionId(prescription.getId());

        medicines.add(newPrescriptionMedicine);
        prescription.setPrescriptionMedicines(medicines);
    }

    @Transactional
    @Modifying
    public String deletePrescription(Long prescriptionId) {
        Appointment appointment=appointmentRepository.findByPrescriptionId(prescriptionId);
        if(appointment!=null) {
            appointment.setPrescription(null);
        }
        Prescription prescription=prescriptionRepository.findById(prescriptionId) .orElseThrow(() -> new RuntimeException("No such prescription found"));
        List<PrescriptionMedicines> prescribedMedicines=prescription.getPrescriptionMedicines();
        prescription.setPrescriptionMedicines(null);
        for(PrescriptionMedicines medicine:prescribedMedicines)
        {
            prescriptionMedicinesRepository.deleteById(medicine.getId());
        }
        prescriptionRepository.deleteById(prescriptionId);
        Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(prescriptionId,"medicine");
        transactionRepository.deleteById(transaction.getId());
        return "Deleted successfully";
    }

    public PrescriptionModel changeStatus(Long prescriptionId, String status) {

            Prescription prescription=prescriptionRepository.findById(prescriptionId).orElseThrow(()-> new RuntimeException("No such prescription found"));
            if (status.equalsIgnoreCase("cancelled")) {
                Transaction transaction = transactionRepository.findByRecordIdAndTransactionOf(prescription.getId(), "medicine");
                transactionRepository.deleteById(transaction.getId());
            } else if (status.toLowerCase().contains("refund")) {
                Transaction transaction = transactionRepository.findByRecordIdAndTransactionOf(prescription.getId(), "medicine");
                if (transaction != null) {
                    transaction.setStatus(status);
                    transactionRepository.save(transaction);
                } else {
                    throw new RuntimeException("Transaction not found for given record Id");
                }
            }
            prescription.setStatus(status);
            prescription=prescriptionRepository.save(prescription);
            Appointment appointment=appointmentRepository.findByPrescriptionId(prescription.getId());
            return Converter.convertAppointmentToAppointmentModel(appointment).getPrescription();

    }

    public AppointmentModel getPrescription(Long prescriptionId) {
        return Converter.convertAppointmentToAppointmentModel(appointmentRepository.findByPrescriptionId(prescriptionRepository.findById(prescriptionId).orElseThrow(()->new RuntimeException("No such prescription found")).getId()));
    }

    public List<PrescriptionModel> getPrescriptionsForPharmacists(String date) {
        List<Appointment> appointments=appointmentRepository.findAll();
        List<PrescriptionModel> prescriptionModels=new ArrayList<>();
        for(Appointment appointment:appointments)
        {
            if(appointment.getPrescription()!=null && (appointment.getPrescription().getStatus().contains("in-progress") || appointment.getPrescription().getStatus().contains("complete")) && appointment.getDate().isEqual(LocalDate.parse(date))) {
                prescriptionModels.add(Converter.convertAppointmentToAppointmentModel(appointment).getPrescription());
            }
        }
        return prescriptionModels;

    }
}
