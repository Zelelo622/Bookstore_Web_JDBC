package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.data.mapper.CategoryMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    private final CrudRepository<Category> repository;
    private final CategoryMapper mapper;

    public CategoryService(CrudRepository<Category> repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<CategoryDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<CategoryDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewCategory(CategoryDto category) {
        repository.save(mapper.toEntity(category));
    }

    public void deleteCategory(Integer id) {
        Optional<Category> сategoryOptional = repository.findById(id);
        if (сategoryOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Категория с id " + id + " не найдена");
        }
    }

    public void updateCategory(CategoryDto category, Integer id) {
        Category entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Категория с id " + id + " не найдена"));
        entity.setName(category.getName());
        repository.update(entity);
    }
}
