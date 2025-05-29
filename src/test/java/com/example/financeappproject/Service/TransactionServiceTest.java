package com.example.financeappproject.Service;

import com.example.financeappproject.DTO.SummaryDTO;
import com.example.financeappproject.DTO.TransactionDTO;
import com.example.financeappproject.DTO.TransactionResponseDTO;
import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Model.Transaction;
import com.example.financeappproject.Repository.CategoryRepository;
import com.example.financeappproject.Repository.TransactionRepository;
import com.example.financeappproject.Service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


public class TransactionServiceTest {
    private TransactionRepository transactionRepository;
    private CategoryRepository categoryRepository;
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionRepository = mock(TransactionRepository.class);
        categoryRepository = mock(CategoryRepository.class);
        transactionService = new TransactionService(transactionRepository, categoryRepository);
    }

    @Test
    @DisplayName("Save a transaction")
    void saveTransaction() {
        TransactionDTO dto = new TransactionDTO(100, "2025-05-27", "Test", "expense", 1L);
        Category category = new Category();
        category.setId(1);
        category.setName("TestName");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(transactionRepository.save(Mockito.<Transaction>any((Transaction.class)))).thenAnswer(i -> i.getArgument(0));

        TransactionResponseDTO result = transactionService.save(dto);

        assertEquals(100.0, result.getAmount());
        assertEquals("Test", result.getDescription());
        assertEquals("TestName", result.getCategoryName());
    }

    @Test
    @DisplayName("Returns all transactions that has been made")
    void getAllTrans() {
        Transaction transaction = new Transaction(1, 200, LocalDate.of(2025, 05, 27), "Gameboy", "Expense");
        Category category = new Category();
        category.setName("Hobby");
        transaction.setCategory(category);

        when(transactionRepository.findAll()).thenReturn(List.of(transaction));

        List<TransactionResponseDTO> result = transactionService.getAll();

        assertEquals(1, result.size());
        assertEquals("Hobby", result.get(0).getCategoryName());
    }

    @Test
    @DisplayName("Get trans based on id")
    void getTransId() {
        Transaction transaction = new Transaction(1, 200, LocalDate.of(2025, 05, 27), "Gameboy", "Expense");
        Category category = new Category();
        category.setName("Hobby");
        transaction.setCategory(category);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));

        Optional<TransactionResponseDTO> result = transactionService.getById(1);

        assertEquals(200, result.get().getAmount());
    }

    @Test
    @DisplayName("Update a transaction")
    void updateTrans() {
        TransactionDTO updatedDto = new TransactionDTO();
        updatedDto.setAmount(55);
        updatedDto.setDate("2025-05-27");
        updatedDto.setDescription("Nintendo");
        updatedDto.setType("Expense");
        updatedDto.setCategoryId(1L);

        Transaction existing = new Transaction();
        existing.setId(1);
        existing.setAmount(200);
        existing.setDate(LocalDate.of(2025, 5, 27));
        existing.setDescription("Gameboy");
        existing.setType("Expense");

        Category category = new Category();
        category.setId(1);
        category.setName("Hobby");

        existing.setCategory(category);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(existing));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(transactionRepository.save(any(Transaction.class))).thenAnswer(i -> i.getArgument(0));

        Optional<TransactionResponseDTO> result = transactionService.update(1, updatedDto);

        assertTrue(result.isPresent());
        assertEquals(55, result.get().getAmount());
        assertEquals("Nintendo", result.get().getDescription());
    }

    @Test
    @DisplayName("Delete an existing transaction on id")
    void deleteTrans() {
        Transaction transaction = new Transaction();
        transaction.setId(1);

        when(transactionRepository.findById(1)).thenReturn(Optional.of(transaction));

        transactionService.delete(1);

        verify(transactionRepository).findById(1);
    }

    @Test
    @DisplayName("Show summary with balance")
    void summary() {
        SummaryDTO summary = new SummaryDTO(100, 50);
        double result = summary.getBalance();
        assertEquals(50, result);
    }
}


