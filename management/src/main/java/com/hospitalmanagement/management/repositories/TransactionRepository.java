package com.hospitalmanagement.management.repositories;

import com.hospitalmanagement.management.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByCreatedAt(LocalDate parse);

    @Query(value = "Delete from transaction where record_id=?1 and transaction_of=?2",nativeQuery = true)
    void deleteByRecordIdAndTransactionOf(Long id, String appointment);

    @Query(value = "Select * from transaction where record_id=?1 and transaction_of=?2",nativeQuery = true)
    Transaction findByRecordIdAndTransactionOf(Long id, String appointment);
}
