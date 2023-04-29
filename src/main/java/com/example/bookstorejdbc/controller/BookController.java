package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.service.BookService;
import com.example.bookstorejdbc.service.BuyerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        Optional<BookDto> bookOptional = service.getById(id);
        if (bookOptional.isPresent()) {
            BookDto book = bookOptional.get();
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            String errorMessage = "Книга не найдена с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
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
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        try {
            service.deleteBook(id);
            return new ResponseEntity<>("Книга удалена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка удаления книги: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/book/new")
    public void addBook(@RequestBody BookDto book) {
        service.addNewBook(book);
    }

    @PutMapping("/book/edit/{id}")
    public ResponseEntity<String> editBook(@RequestBody BookDto book, @PathVariable Integer id) {
        try {
            service.updateBook(book, id);
            return new ResponseEntity<>("Книга обновлена", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка обновления книги: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
