package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result,Long> {

    @Query(value = "select * from result where appointment_tests_id=?1",nativeQuery = true)
    Result findByAppointmentTestId(Long id);
}
