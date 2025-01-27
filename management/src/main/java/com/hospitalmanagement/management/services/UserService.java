package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.Users;
import com.hospitalmanagement.management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Users login(String email, String password) {
        return userRepository.login(email, password);
    }

    public Users getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("No such user found"));
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users addUser(Users user) {
        user.setDepartment(user.getDepartment().toUpperCase());
        return userRepository.save(user);
    }

    public Users editUser(Users user, Long id) {
        Users fetchedUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("No such user found"));
        fetchedUser.setEmail(user.getEmail());
        fetchedUser.setName(user.getName());
        fetchedUser.setRole(user.getRole());
        fetchedUser.setPassword(user.getPassword());
        fetchedUser.setDepartment(user.getDepartment().toUpperCase());
        return userRepository.save(fetchedUser);
    }

    public List<String> getDepartments() {

        List<Users> users = getAllUsers();
        List<String> departments = new ArrayList<>();
        for (Users user : users) {
            if (!departments.contains(user.getDepartment().toUpperCase())) {
                departments.add(user.getDepartment().toUpperCase());
            }
        }
        return departments;
    }

    public List<Users> getUsersByDepartmentName(String name) {
        return userRepository.findByDepartment(name.toUpperCase());
    }

    public List<String> getDepartmentsByRole(String role) {
        return userRepository.findDepartmentsByRole(role);
    }
}
