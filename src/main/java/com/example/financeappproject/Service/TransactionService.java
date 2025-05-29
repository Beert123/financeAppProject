package com.example.financeappproject.Service;

import com.example.financeappproject.DTO.SummaryDTO;
import com.example.financeappproject.DTO.TransactionDTO;
import com.example.financeappproject.DTO.TransactionResponseDTO;
import com.example.financeappproject.Mapper.TransactionMapper;
import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Model.Transaction;
import com.example.financeappproject.Repository.CategoryRepository;
import com.example.financeappproject.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    private final CategoryRepository categoryRepository;

    public TransactionService(TransactionRepository repository, CategoryRepository categoryRepository) {
        this.repository = repository;
        this.categoryRepository = categoryRepository;
    }

    public TransactionResponseDTO save(TransactionDTO dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Transaction transaction = TransactionMapper.toEntity(dto, category);
        repository.save(transaction);
        return TransactionMapper.toResponseDTO(transaction);
    }

    public List<TransactionResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(TransactionMapper::toResponseDTO)
                .toList();
    }

    public Optional<TransactionResponseDTO> getById(int id) {
        return repository.findById(id)
                .map(TransactionMapper::toResponseDTO);
    }

    public Optional<TransactionResponseDTO> update(int id, TransactionDTO updated) {
        return repository.findById(id).map(transaction -> {
            Category category = categoryRepository.findById((long) updated.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            TransactionMapper.updateEntityFromDTO(transaction, updated, category);
            Transaction saved = repository.save(transaction);
            return TransactionMapper.toResponseDTO(saved);
        });
    }

    public void delete(int id) {
        Transaction transaction = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));
        repository.delete(transaction);
    }

    public SummaryDTO getSummary() {
        List<Transaction> allTrans = repository.findAll();
        double earning = Optional.ofNullable(repository.sumAmountByType("income")).orElse(0.0);
        double expense = Optional.ofNullable(repository.sumAmountByType("expense")).orElse(0.0);
        for (Transaction transaction : allTrans) {
            String type = transaction.getType().toLowerCase();

            if (type.equals("earning")) {
                earning += transaction.getAmount();
            } else if (type.equals("expense")) {
                expense += transaction.getAmount();
            }
        }
        return new SummaryDTO(earning, expense);
    }

}
