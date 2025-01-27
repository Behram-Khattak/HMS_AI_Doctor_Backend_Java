package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.Medicine;
import com.hospitalmanagement.management.repositories.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineService {
    @Autowired
    MedicineRepository medicineRepository;

    public Medicine addMedicine(Medicine medicine)
    {
        return medicineRepository.save(medicine);
    }

    public Medicine getMedicine(Long id){
        return medicineRepository.findById(id) .orElseThrow(() -> new RuntimeException("No such medicine found"));
    }

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findAll();
    }

    public Medicine editMedicine(Long id,Medicine medicine)
    {
        Optional<Medicine> optionalMedicine=medicineRepository.findById(id);
        if(optionalMedicine.isPresent())
        {
            Medicine fetchedMedicine=optionalMedicine.get();
            medicine.setId(fetchedMedicine.getId());
            return medicineRepository.save(medicine);
        }
        else
        {
            throw new RuntimeException("Medicine not found");
        }
    }
}
