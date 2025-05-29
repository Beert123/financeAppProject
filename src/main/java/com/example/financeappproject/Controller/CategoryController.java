package com.example.financeappproject.Controller;

import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Repository.CategoryRepository;
import com.example.financeappproject.Service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping("/categoriesPost")
    public Category create(@RequestBody Category category) {
        return service.save(category);
    }

    @GetMapping("/categories")
    public List<Category> getAll() {
        return service.getAll();
    }

    @GetMapping("/category/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getById(id).orElse(null);
    }
}
