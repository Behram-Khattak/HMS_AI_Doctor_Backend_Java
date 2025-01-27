package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Result;
import com.hospitalmanagement.management.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/result")
public class ResultController {

    @Autowired
    ResultService resultService;

    @PostMapping("/{appointmentId}")
    public Result addResult(@RequestBody Result result,@PathVariable Long appointmentId){
        return resultService.addResult(result,appointmentId);
    }

    @GetMapping("/{id}")
    public Result getResult(@PathVariable Long id)
    {
        return resultService.getResult(id);
    }

    @GetMapping("/test/{appointmentTestId}")
    public Result getResultByAppointmentTestId(@PathVariable Long appointmentTestId)
    {
        return resultService.getByAppointmentTestId(appointmentTestId);
    }
}
