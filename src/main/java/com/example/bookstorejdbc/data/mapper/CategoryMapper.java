package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public CategoryDto toDto(Category entity) {
        CategoryDto dto = new CategoryDto();
        dto.setCategory_id(entity.getCategory_id());
        dto.setName(entity.getName());
        return dto;
    }

    public Category toEntity(CategoryDto dto) {
        Category entity = new Category();
        entity.setCategory_id(dto.getCategory_id());
        entity.setName(dto.getName());
        return entity;
    }
}
