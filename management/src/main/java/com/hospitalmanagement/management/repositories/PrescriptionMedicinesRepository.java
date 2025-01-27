package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.PrescriptionMedicines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionMedicinesRepository extends JpaRepository<PrescriptionMedicines,Long> {
}
