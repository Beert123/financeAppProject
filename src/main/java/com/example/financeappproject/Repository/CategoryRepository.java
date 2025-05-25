package com.example.financeappproject.Repository;

import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
