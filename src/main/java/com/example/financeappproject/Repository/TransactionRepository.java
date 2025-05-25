package com.example.financeappproject.Repository;

import com.example.financeappproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    //Spring læser metodenavn og oversætter det til SQL-query
    List<Transaction> findByDateBetweenAndCategory_Name(LocalDate startDate, LocalDate endDate, String categoryName);
}
