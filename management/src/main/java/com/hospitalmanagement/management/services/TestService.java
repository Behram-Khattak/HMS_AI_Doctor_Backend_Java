package com.hospitalmanagement.management.services;

import com.hospitalmanagement.management.entities.Appointment;
import com.hospitalmanagement.management.entities.AppointmentTests;
import com.hospitalmanagement.management.entities.Test;
import com.hospitalmanagement.management.entities.Transaction;
import com.hospitalmanagement.management.models.AppointmentModel;
import com.hospitalmanagement.management.models.AppointmentTestsModel;
import com.hospitalmanagement.management.repositories.AppointmentRepository;
import com.hospitalmanagement.management.repositories.AppointmentTestsRepository;
import com.hospitalmanagement.management.repositories.TestRepository;
import com.hospitalmanagement.management.repositories.TransactionRepository;
import com.hospitalmanagement.management.utils.Converter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AppointmentTestsRepository appointmentTestsRepository;

    @Autowired
    TestRepository testRepository;

    public List<Test> getAllTests(){
        return testRepository.findAll();
    }

    public Test addTest(Test test){
        return testRepository.save(test);
    }

    public AppointmentModel assignTest(Long appointmentId, Long testId) {
        Appointment appointment=appointmentRepository.findById(appointmentId) .orElseThrow(() -> new RuntimeException("No such appointment found"));
        AppointmentTests appointmentTest=new AppointmentTests();
        appointmentTest.setTest(testRepository.findById(testId).get());
        appointmentTest.setAppointment(appointment);
        appointmentTest.setStatus("scheduled");
        appointmentTest.setDate(appointment.getDate());
       List<AppointmentTests> appointmentTests=appointment.getAppointmentTests();
       appointmentTests.add(appointmentTest);
       appointment.setAppointmentTests(appointmentTests);

       appointment=appointmentRepository.save(appointment);
       for(AppointmentTests test:appointment.getAppointmentTests())
       {

           Transaction transaction=new Transaction();
           transaction.setStatus("valid");
           transaction.setTransactionOf("test");
           transaction.setPatient(appointment.getPatient());
           transaction.setAmount(test.getTest().getPrice());
           transaction.setCreatedAt(appointment.getDate());
           transaction.setRecordId(test.getId());
           if(transactionRepository.findByRecordIdAndTransactionOf(transaction.getRecordId(),transaction.getTransactionOf())==null) {
               transactionRepository.save(transaction);
           }
       }
       return Converter.convertAppointmentToAppointmentModel(appointment);
    }

    @Transactional
    @Modifying
    public String deleteTest(Long id) {
        Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(id,"test");
        if(transaction!=null) {
            transactionRepository.deleteById(transaction.getId());
        }
        AppointmentTests appointmentTests=appointmentTestsRepository.findById(id) .orElseThrow(() -> new RuntimeException("No such appointment test found"));
        Appointment appointment=appointmentRepository.findById(appointmentTests.getAppointment().getId()) .orElseThrow(() -> new RuntimeException("No such appointment found"));
        List<AppointmentTests> appointmentTestsList=appointment.getAppointmentTests();
        appointmentTestsList.remove(appointmentTests);
        appointment.setAppointmentTests(appointmentTestsList);
        appointmentRepository.save(appointment);
       appointmentTestsRepository.delete(appointmentTests);
        return "Deleted successfully";
    }

    public AppointmentTestsModel changeStatus(Long assignedTestId, String status) {
        AppointmentTests appointmentTests=appointmentTestsRepository.findById(assignedTestId) .orElseThrow(() -> new RuntimeException("No such appointment test found"));
        appointmentTests.setStatus(status);
        if(status.equalsIgnoreCase("cancelled"))
        {
            Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(appointmentTests.getId(),"test");
            transactionRepository.deleteById(transaction.getId());
        } else if (status.toLowerCase().contains("refund")) {
            Transaction transaction=transactionRepository.findByRecordIdAndTransactionOf(appointmentTests.getId(),"test");
            if(transaction!=null)
            {
                transaction.setStatus(status);
                transactionRepository.save(transaction);
            }
            else{
                throw new RuntimeException("Transaction not found for given record Id");
            }
        }
        appointmentTests= appointmentTestsRepository.save(appointmentTests);
        return Converter.convertAppointmentTestToAppointmentTestModel(appointmentTests);
    }

    public List<AppointmentTestsModel> getAppointedTests(Long appointmentId) {
        Appointment appointment=appointmentRepository.findById(appointmentId).orElseThrow(()->new RuntimeException("No such appointment found"));
        List<AppointmentTestsModel> appointmentTestsModels=new ArrayList<>();
        for (AppointmentTests test:appointment.getAppointmentTests())
        {
            appointmentTestsModels.add(Converter.convertAppointmentTestToAppointmentTestModel(test));
        }
        return appointmentTestsModels;
    }

    public List<AppointmentTestsModel> getAppointedTestsByDateAndDepartment(String date, String department) {
        List<AppointmentTests> appointmentTests=appointmentTestsRepository.findAllByDate(LocalDate.parse(date));
        appointmentTests=appointmentTests.stream().filter(t->t.getTest().getDepartment().equalsIgnoreCase(department)).toList();
        appointmentTests=appointmentTests.stream().filter(t->(t.getStatus().equalsIgnoreCase("in-progress") || t.getStatus().equalsIgnoreCase("completed"))).toList();
        List<AppointmentTestsModel> appointmentTestsModels=new ArrayList<>();
        for(AppointmentTests appointmentTest:appointmentTests)
        {
            appointmentTestsModels.add(Converter.convertAppointmentTestToAppointmentTestModel(appointmentTest));
        }
        return appointmentTestsModels;
    }
}
