package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test,Long> {
}
