package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.repository.CategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public BookDto toDto(Book entity) {
        BookDto dto = new BookDto();
        dto.setAuthor(entity.getAuthor());
        dto.setTitle(entity.getTitle());
        dto.setPrice(entity.getPrice());
        dto.setCategory_id(entity.getCategory_id());
        dto.setPublishing_house_id(entity.getPublishing_house_id());
        return dto;
    }

    public Book toEntity(BookDto dto) {
        Book entity = new Book();
        entity.setAuthor(dto.getAuthor());
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setCategory_id(dto.getCategory_id());
        entity.setPublishing_house_id(dto.getPublishing_house_id());
        return entity;
    }
}
