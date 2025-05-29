package com.example.financeappproject.Repository;

import com.example.financeappproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findByDateBetweenAndCategory_Name(LocalDate startDate, LocalDate endDate, String categoryName);

    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = :type")
    Double sumAmountByType(@Param("type") String type);
}
