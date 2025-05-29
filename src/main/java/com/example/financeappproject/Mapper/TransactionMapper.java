package com.example.financeappproject.Mapper;

import com.example.financeappproject.DTO.TransactionDTO;
import com.example.financeappproject.DTO.TransactionResponseDTO;
import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Model.Transaction;

import java.time.LocalDate;

public class TransactionMapper {
    public static Transaction toEntity(TransactionDTO dto, Category category) {
        Transaction t = new Transaction();
        t.setAmount(dto.getAmount());
        t.setDate(LocalDate.parse(dto.getDate()));
        t.setDescription(dto.getDescription());
        t.setType(dto.getType());
        t.setCategory(category);
        return t;
    }

    public static TransactionResponseDTO toResponseDTO(Transaction t) {
        TransactionResponseDTO dto = new TransactionResponseDTO();
        dto.setId(t.getId());
        dto.setAmount(t.getAmount());
        dto.setDate(t.getDate());
        dto.setDescription(t.getDescription());
        dto.setType(t.getType());
        dto.setCategoryName(t.getCategory().getName());
        return dto;
    }

    public static void updateEntityFromDTO(Transaction existing, TransactionDTO dto, Category category) {
        existing.setAmount(dto.getAmount());
        existing.setDate(LocalDate.parse(dto.getDate()));
        existing.setDescription(dto.getDescription());
        existing.setType(dto.getType());
        existing.setCategory(category);
    }
}
