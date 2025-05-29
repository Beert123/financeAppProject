package com.example.financeappproject.Service;

import com.example.financeappproject.Model.Category;
import com.example.financeappproject.Repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CategoryServiceTest {
    private CategoryRepository categoryRepository;
    private CategoryService categoryService;

    @BeforeEach
    void setup() {
        categoryRepository = mock(CategoryRepository.class);
        categoryService = new CategoryService(categoryRepository);
    }

    @Test
    @DisplayName("save a category")
    void save() {
        Category category = new Category();
        category.setName("PS4");
        category.setType("Gaming");

        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findAll()).thenReturn(List.of(category));

        Category result = categoryService.save(category);

        assertEquals("Gaming", result.getType());
        assertEquals(1, categoryService.getAll().size());
    }

    @Test
    @DisplayName("Get all categories")
    void getAll() {
        Category category = new Category();
        category.setName("PSP");
        category.setType("Gaming");

        when(categoryRepository.findAll()).thenReturn(List.of(category));

        List<Category> result = categoryService.getAll();

        assertEquals(1, result.size());
        assertEquals("PSP", result.get(0).getName());
    }

    @Test
    @DisplayName("get category by id")
    void getById() {
        Category category = new Category();
        category.setName("Guitar");
        category.setType("Musik");

        when(categoryRepository.findById((long) 1)).thenReturn(Optional.of(category));

        Optional<Category> result = categoryService.getById((long) 1);

        assertEquals("Guitar", result.get().getName());
    }
}