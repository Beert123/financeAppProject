package com.example.financeappproject.Service;

import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }
    public Category save(Category category){
        return repository.save(category);
    }
    public List<Category> getAll(){
        return repository.findAll();
    }
    public Optional<Category> getById(Long id){
        return repository.findById(id);
    }
}
