package com.example.financeappproject.Service;

import com.example.financeappproject.Model.Transaction;
import com.example.financeappproject.Repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {
    private final TransactionRepository repository;
    public TransactionService(TransactionRepository repository){
        this.repository = repository;
    }
    public Transaction save(Transaction transaction){
       return repository.save(transaction);
    }
    public List<Transaction> getAll(){
        return repository.findAll();
    }
    public Optional<Transaction> getById(int id){
        return repository.findById(id);
    }
}
