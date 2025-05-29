package com.example.financeappproject.Controller;

import com.example.financeappproject.DTO.SummaryDTO;
import com.example.financeappproject.DTO.TransactionDTO;
import com.example.financeappproject.DTO.TransactionResponseDTO;
import com.example.financeappproject.Model.Transaction;
import com.example.financeappproject.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Giver en ResponsBody
@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @PostMapping("/trans")
    public TransactionResponseDTO create(@RequestBody TransactionDTO dto) {
        return service.save(dto);
    }

    @GetMapping("/trans")
    public List<TransactionResponseDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/trans/{id}")
    public Optional<TransactionResponseDTO> getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PutMapping("/trans/{id}")
    public ResponseEntity<TransactionResponseDTO> update(@PathVariable("id") int id, @RequestBody TransactionDTO updated) {
        return service.update(id, updated)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/trans/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/summary")
    public SummaryDTO getSummary() {
        return service.getSummary();
    }
}
