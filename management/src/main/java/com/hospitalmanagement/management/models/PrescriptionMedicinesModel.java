package com.hospitalmanagement.management.models;

import com.hospitalmanagement.management.entities.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionMedicinesModel {
    Long id;

    Medicine medicine;

    double quantity;
    double price;


}
