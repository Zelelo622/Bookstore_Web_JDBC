package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO book (title, author, price, category_id, publishing_house_id) VALUES (?, ?, ?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getPrice(), book.getCategory_id(), book.getPublishing_house_id());
    }

    @Override
    public int update(Book book) {
        Optional<Category> category = categoryRepository.findById(book.getCategory_id());
        Optional<PublishingHouse> publishingHouse = publishingHouseRepository.findById(book.getPublishing_house_id());
        book.setCategory_id(category.get().getCategory_id());
        book.setPublishing_house_id(publishingHouse.get().getPublishing_house_id());
        return jdbcTemplate.update("UPDATE book SET title=?, author=?, price=?, category_id=?, publishing_house_id=? WHERE book_id=?",
                book.getTitle(), book.getAuthor(), book.getPrice(), book.getCategory_id(), book.getPublishing_house_id(), book.getBook_id());
    }

    @Override
    public Optional<Book> findById(Integer id) {
        String sql = "SELECT * FROM book WHERE book_id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Book.class), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM book WHERE book_id=?", id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM book", BeanPropertyRowMapper.newInstance(Book.class));
    }

    public List<Book> findByCategoryId(Integer categoryId) {
        return jdbcTemplate.query("SELECT * FROM book WHERE category_id = ?", BeanPropertyRowMapper.newInstance(Book.class), categoryId);
    }

    public List<Book> findByPubHouseId(Integer publishing_house_id) {
        return jdbcTemplate.query("SELECT * FROM book WHERE publishing_house_id = ?", BeanPropertyRowMapper.newInstance(Book.class), publishing_house_id);
    }

    public List<Book> getAllById(List<Integer> bookIds) {
        String query = "SELECT * FROM book WHERE book_id IN (" + String.join(",", Collections.nCopies(bookIds.size(), "?")) + ")";
        return jdbcTemplate.query(query, bookIds.toArray(), new BeanPropertyRowMapper<>(Book.class));
    }
}
