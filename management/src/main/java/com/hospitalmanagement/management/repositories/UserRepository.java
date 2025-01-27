package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<Users,Long> {

    @Query(value = "Select * from users where email=?1 and password=?2",nativeQuery = true)
    Users login(String email, String password);

    @Query(value = "Select * from users where department=?1",nativeQuery = true)
    List<Users> findByDepartment(String name);

    @Query(value = "Select department from users where role=?1",nativeQuery = true)
    List<String> findDepartmentsByRole(String role);
}
