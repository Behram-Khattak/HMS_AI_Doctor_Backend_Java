package com.hospitalmanagement.management.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Patient {

    @Id
    String id;
    String fullName;
    LocalDate dob;
    String gender;
    String address;
    String phoneNumber;
    String emailAddress;
}
