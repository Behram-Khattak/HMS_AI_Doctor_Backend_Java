package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.AppointmentTests;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentTestsRepository extends JpaRepository<AppointmentTests,Long> {
    List<AppointmentTests> findAllByDate(LocalDate parse);
}
