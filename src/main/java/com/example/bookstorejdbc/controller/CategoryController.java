package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping("/category")
    public List<CategoryDto> getAllCategory() {
        return service.getAll();
    }

    @GetMapping("/category/{id}")
    public CategoryDto getCategoryById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/category/delete/{id}")
    public void deleteCategory(@PathVariable Integer id) {
        service.deleteCategory(id);
    }

    @PostMapping("/category/new")
    public void addCategory(@RequestBody CategoryDto category) {
        service.addNewCategory(category);
    }

    @PutMapping("/category/edit/{id}")
    public void editCategory(@RequestBody CategoryDto category, @PathVariable Integer id) {
        service.updateCategory(category, id);
    }
}
