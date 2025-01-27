package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.AppointmentTests;
import com.hospitalmanagement.management.entities.Result;
import com.hospitalmanagement.management.repositories.AppointmentRepository;
import com.hospitalmanagement.management.repositories.AppointmentTestsRepository;
import com.hospitalmanagement.management.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    AppointmentTestsRepository appointmentTestsRepository;

    public Result addResult(Result result,Long appointmentId)
    {
        AppointmentTests appointmentTests=appointmentTestsRepository.findById(appointmentId) .orElseThrow(() -> new RuntimeException("No such appointment found"));

        appointmentTests.setStatus("completed");
        appointmentTestsRepository.save(appointmentTests);
        result.setAppointmentTests(appointmentTests);
        return resultRepository.save(result);
    }
    public Result getResult(Long id)
    {
        return resultRepository.findById(id) .orElseThrow(() -> new RuntimeException("No such result found"));
    }
    public Result getByAppointmentTestId(Long id)
    {
        return resultRepository.findByAppointmentTestId(id);
    }
}
