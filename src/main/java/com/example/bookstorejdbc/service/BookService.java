package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Buyer;
import com.example.bookstorejdbc.data.mapper.BookMapper;
import com.example.bookstorejdbc.repository.BookRepository;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {
    private final BookRepository repository;
    private final BookMapper mapper;

    public BookService(BookRepository repository, BookMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BookDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<BookDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewBook(BookDto book) {
        repository.save(mapper.toEntity(book));
    }

    public void deleteBook(Integer id) {
        Optional<Book> bookOptional = repository.findById(id);
        if (bookOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Книга с id " + id + " не найдена");
        }
    }

    public void updateBook(BookDto book, Integer id) {
        Book entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель с id " + id + " не найден"));
        entity.setTitle(book.getTitle());
        entity.setAuthor(book.getAuthor());
        entity.setPrice(book.getPrice());
        entity.setCategory_id(book.getCategory_id());
        entity.setPublishing_house_id(book.getPublishing_house_id());
        repository.update(entity);
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
