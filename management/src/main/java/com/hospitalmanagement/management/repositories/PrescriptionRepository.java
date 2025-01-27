package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Prescription;
import com.hospitalmanagement.management.entities.PrescriptionMedicines;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription,Long> {

    @Query(value="Select * from prescription_medicines where prescription_id=?1",nativeQuery = true)
    List<PrescriptionMedicines> getPrescribedMedicines(Long prescriptionId);

    @Modifying
    @Transactional
    @Query(value="INSERT INTO PrescriptionMedicine (prescription_id, medicine_id, quantity, price) VALUES (?1, ?2, ?3, ?4)",nativeQuery = true)
    PrescriptionMedicines prescribeMedicine(Long prescriptionId,Long medicineId,Long quantity,double price);
}
