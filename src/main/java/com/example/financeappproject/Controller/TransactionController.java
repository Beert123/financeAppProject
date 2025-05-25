package com.example.financeappproject.Controller;

import com.example.financeappproject.Model.Transaction;
import com.example.financeappproject.Service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Giver en ResponsBody
@RestController

public class TransactionController {
    private TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }
    @PostMapping
    public Transaction create(@RequestBody Transaction transaction){
        return service.save(transaction);
    }
    @GetMapping("/trans")
    public List<Transaction> getAll(){
        return service.getAll();
    }
}
