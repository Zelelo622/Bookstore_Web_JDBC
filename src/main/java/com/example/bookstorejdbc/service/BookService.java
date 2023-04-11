package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.mapper.BookMapper;
import com.example.bookstorejdbc.repository.BookRepository;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {
//    private final CrudRepository<Book> repository;
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BookDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public BookDto getById(Integer id) {
        return mapper.toDto(repository.findById(id));
    }

    public void addNewBook(BookDto book) {
        repository.save(mapper.toEntity(book));
    }

    public void deleteBook(Integer id) {
        repository.deleteById(id);
    }

    public void updateBook(BookDto buyer, Integer id) {
        Book oldBook = repository.findById(id);
        Book newBook = mapper.toEntity(buyer);
        newBook.setBook_id(oldBook.getBook_id());
        repository.update(newBook);
    }

    public List<BookDto> getByCategoryId(Integer categoryId) {
        List<Book> books = repository.findByCategoryId(categoryId);
        return books.stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public List<BookDto> getByPubHouseId(Integer publishing_house_id) {
        List<Book> books = repository.findByPubHouseId(publishing_house_id);
        return books.stream().map(mapper::toDto).collect(Collectors.toList());
    }
}
