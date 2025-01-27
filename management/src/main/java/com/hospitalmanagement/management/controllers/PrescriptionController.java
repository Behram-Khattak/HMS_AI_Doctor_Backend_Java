package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Prescription;
import com.hospitalmanagement.management.models.AppointmentModel;
import com.hospitalmanagement.management.models.PrescriptionModel;
import com.hospitalmanagement.management.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/prescription")
public class PrescriptionController {

    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping("/{appointmentId}")
    public PrescriptionModel addPrescription(@RequestBody Prescription prescription, @PathVariable Long appointmentId)
    {
        return prescriptionService.addPrescription(prescription,appointmentId);
    }

//    @PutMapping("/{prescriptionId}")
//    public Prescription addMedicineToPrescription(@RequestBody PrescriptionMedicines medicines,@PathVariable Long prescriptionId)
//    {
//        return prescriptionService.addMedicine(prescriptionId,medicines);
//    }

    @DeleteMapping("/{prescriptionId}")
    public String deletePrescription(@PathVariable Long prescriptionId)
    {
        return prescriptionService.deletePrescription(prescriptionId);
    }

    @PutMapping("/change-status/{prescriptionId}/{status}")
    public PrescriptionModel changeStatus(@PathVariable Long prescriptionId, @PathVariable String status)
    {
        return prescriptionService.changeStatus(prescriptionId,status);
    }


    @GetMapping("/{prescriptionId}")
    public AppointmentModel getPrescription(@PathVariable Long prescriptionId)
    {
        return prescriptionService.getPrescription(prescriptionId);
    }

    @GetMapping("/get-prescriptions/{date}")
    public List<PrescriptionModel> getPrescriptionsForPharmacists(@PathVariable String date){
        return prescriptionService.getPrescriptionsForPharmacists(date);
    }
}
