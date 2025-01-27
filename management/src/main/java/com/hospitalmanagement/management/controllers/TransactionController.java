package com.hospitalmanagement.management.controllers;

import com.hospitalmanagement.management.entities.Transaction;
import com.hospitalmanagement.management.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/transaction")
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @GetMapping("/{date}")
    public List<Transaction> getTransactionByDate(@PathVariable LocalDate date)
    {
        return transactionService.getTransactionsByDate(date);
    }

    @PutMapping("/change-status/{id}/{status}")
    public Transaction changeStatus(@PathVariable Long id,@PathVariable String status)
    {
        return transactionService.changeStatus(id,status);
    }
}
