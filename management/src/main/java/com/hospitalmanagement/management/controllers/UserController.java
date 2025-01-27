package com.hospitalmanagement.management.controllers;

// import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.hospitalmanagement.management.entities.Users;
import com.hospitalmanagement.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/user")
    public Users addUser(@RequestBody Users user) {
        return userService.addUser(user);
    }

    @PostMapping("/user/login")
    public Users login(@RequestBody Users user) {
        return userService.login(user.getEmail(), user.getPassword());
    }

    @GetMapping("/user/{id}")
    public Users getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @GetMapping("/user")
    public List<Users> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/user/{id}")
    public Users updateUser(@PathVariable Long id, @RequestBody Users user) {
        return userService.editUser(user, id);
    }

    @GetMapping("/departments")
    public List<String> getDepartments() {
        return userService.getDepartments();
    }

    @GetMapping("/doctors/{department}")
    public List<Users> getDoctorsOfDepartment(@PathVariable String department) {
        return userService.getUsersByDepartmentName(department);
    }

    @GetMapping("/get-departments-by-role/{role}")
    public List<String> getDepartmentsByRole(@PathVariable String role) {
        return userService.getDepartmentsByRole(role);
    }
}
