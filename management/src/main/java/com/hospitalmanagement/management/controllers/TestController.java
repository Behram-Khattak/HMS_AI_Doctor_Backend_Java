package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Test;
import com.hospitalmanagement.management.models.AppointmentModel;
import com.hospitalmanagement.management.models.AppointmentTestsModel;
import com.hospitalmanagement.management.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/test")
public class TestController {

    @Autowired
    TestService testService;

    @PostMapping
    public Test addTest(@RequestBody Test test){
        return testService.addTest(test);
    }

    @GetMapping
    public List<Test> getAllTests(){
        return testService.getAllTests();
    }

    @PostMapping("/{testId}/assign-to-appointment/{appointmentId}")
    public AppointmentModel assignTest(@PathVariable Long appointmentId, @PathVariable Long testId)
    {
        return testService.assignTest(appointmentId,testId);
    }

    @PutMapping("/change-status/{assignedTestId}/{status}")
    public AppointmentTestsModel changeStatus(@PathVariable Long assignedTestId,@PathVariable String status)
    {
        return testService.changeStatus(assignedTestId,status);
    }

    @DeleteMapping("/{id}")
    public String deleteAssignTest(@PathVariable Long id)
    {
        return testService.deleteTest(id);
    }

    @GetMapping("/get-appointed-test/{appointmentId}")
    public List<AppointmentTestsModel> getAssignedTests(@PathVariable Long appointmentId){
        return testService.getAppointedTests(appointmentId);
    }

    @GetMapping("/get-tests-by-date-and-department/{date}/{department}")
    public List<AppointmentTestsModel> getAssignedTestsByDateAndDepartment(@PathVariable String date, @PathVariable String department){
        return testService.getAppointedTestsByDateAndDepartment(date,department);
    }
}
