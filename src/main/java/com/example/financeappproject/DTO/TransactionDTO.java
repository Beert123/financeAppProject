package com.example.financeappproject.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import java.time.LocalDate;

public class TransactionDTO {
    private double amount;
    private String date;
    private String description;
    private String type;
    private Long categoryId; //

    public TransactionDTO(double amount, String date, String description, String type, Long categoryId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.type = type;
        this.categoryId = categoryId;
    }

    public TransactionDTO() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}


