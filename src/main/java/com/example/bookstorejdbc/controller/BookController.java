package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.service.BookService;
import com.example.bookstorejdbc.service.BuyerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/book")
    public List<BookDto> getAllBook() {
        return service.getAll();
    }

    @GetMapping("/book/{id}")
    public BookDto getBookById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @GetMapping("/book/category")
    public List<BookDto> getBooksByCategoryId(@RequestParam("categoryId") Integer categoryId) {
        return service.getByCategoryId(categoryId);
    }

    @GetMapping("/book/publishing")
    public List<BookDto> getBooksByPubHouseId(@RequestParam("publishingHouseId") Integer publishingHouseId) {
        return service.getByPubHouseId(publishingHouseId);
    }

    @DeleteMapping("/book/delete/{id}")
    public void deleteBook(@PathVariable Integer id) {
        service.deleteBook(id);
    }

    @PostMapping("/book/new")
    public void addBook(@RequestBody BookDto book) {
        service.addNewBook(book);
    }

    @PutMapping("/book/edit/{id}")
    public void editBook(@RequestBody BookDto book, @PathVariable Integer id) {
        service.updateBook(book, id);
    }
}
