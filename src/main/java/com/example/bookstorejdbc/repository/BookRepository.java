package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
public class BookRepository implements CrudRepository<Book> {

    private final JdbcTemplate jdbcTemplate;
    private final CategoryRepository categoryRepository;

    private final PublishingHouseRepository publishingHouseRepository;

    public BookRepository(JdbcTemplate jdbcTemplate, CategoryRepository categoryRepository, PublishingHouseRepository publishingHouseRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.categoryRepository = categoryRepository;
        this.publishingHouseRepository = publishingHouseRepository;
    }

    @Override
    @Transactional
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO book (title, author, price, category_id, publishing_house_id) VALUES (?, ?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPrice(), book.getCategory_id(), book.getPublishing_house_id());
    }

    @Override
    @Transactional
    public int update(Book book) {
        Category category = categoryRepository.findById(book.getCategory_id());
        PublishingHouse publishingHouse = publishingHouseRepository.findById(book.getPublishing_house_id());
        book.setCategory_id(category.getCategory_id());
        book.setPublishing_house_id(publishingHouse.getPublishing_house_id());
        return jdbcTemplate.update("UPDATE book SET title=?, author=?, price=?, category_id=?, publishing_house_id=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getPrice(), book.getCategory_id(), book.getPublishing_house_id(), book.getBook_id());
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(Integer id) {
        Book book = jdbcTemplate.queryForObject("SELECT * FROM books WHERE book_id = ?",
                BeanPropertyRowMapper.newInstance(Book.class), id);

        return book;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", BeanPropertyRowMapper.newInstance(Book.class));
    }

    @Transactional(readOnly = true)
    public List<Book> findByCategoryId(Integer categoryId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE category_id = ?", BeanPropertyRowMapper.newInstance(Book.class), categoryId);
    }

    @Transactional(readOnly = true)
    public List<Book> findByPubHouseId(Integer publishing_house_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE publishing_house_id = ?", BeanPropertyRowMapper.newInstance(Book.class), publishing_house_id);
    }

    @Transactional
    public List<Book> getAllById(List<Integer> bookIds) {
        String query = "SELECT * FROM book WHERE book_id IN (" + String.join(",", Collections.nCopies(bookIds.size(), "?")) + ")";
        return jdbcTemplate.query(query, bookIds.toArray(), new BeanPropertyRowMapper<>(Book.class));
    }
}
