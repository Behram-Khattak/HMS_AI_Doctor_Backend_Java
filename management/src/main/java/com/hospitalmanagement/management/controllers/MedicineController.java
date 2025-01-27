package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Medicine;
import com.hospitalmanagement.management.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/medicine")
public class MedicineController {

    @Autowired
    MedicineService medicineService;

    @PostMapping
    public Medicine addMedicine(@RequestBody Medicine medicine)
    {
        return medicineService.addMedicine(medicine);
    }

    @GetMapping
    public List<Medicine> getAllMedicines()
    {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public Medicine getMedicine(@PathVariable Long id)
    {
        return medicineService.getMedicine(id);
    }

    @PutMapping("/{id}")
    public Medicine editMedicine(@PathVariable Long id,@RequestBody Medicine medicine)
    {
        return medicineService.editMedicine(id,medicine);
    }
}
