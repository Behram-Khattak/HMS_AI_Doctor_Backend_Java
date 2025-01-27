package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Appointment;
import com.hospitalmanagement.management.entities.AppointmentTests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query(value="SELECT MAX(a.token) FROM Appointment a WHERE a.doctor_id = ?1 AND a.date= ?2",nativeQuery = true)
    public Long getToken(Long id, LocalDate date);

    Appointment findByPrescriptionId(Long prescriptionId);

    @Query(value="select * from appointment where doctor_id=?1 and date=?2",nativeQuery = true)
    List<Appointment> findAllByDoctorIdAndDate(Long doctorId, LocalDate date);

    @Query(value="select * from appointment where patient_id=?1",nativeQuery = true)
    List<Appointment> findAllByPatientId(Long patientId);

    @Query(value = "Select * from appointment where date=?1",nativeQuery = true)
    List<Appointment> findAllByDate(LocalDate date);
}
