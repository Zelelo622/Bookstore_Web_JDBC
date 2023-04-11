package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.data.mapper.CategoryMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public CategoryDto getById(Integer id) {
        return mapper.toDto(repository.findById(id));
    }

    public void addNewCategory(CategoryDto category) {
        repository.save(mapper.toEntity(category));
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }

    public void updateCategory(CategoryDto category, Integer id) {
        Category oldCategory = repository.findById(id);
        Category newCategory = mapper.toEntity(category);
        newCategory.setCategory_id(oldCategory.getCategory_id());
        repository.update(newCategory);
    }
}
