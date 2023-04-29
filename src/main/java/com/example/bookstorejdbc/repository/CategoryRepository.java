package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements CrudRepository<Category> {

    private JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Category category) {
        return jdbcTemplate.update("INSERT INTO category (name) VALUES (?)",
                category.getName());
    }

    @Override
    public int update(Category category) {
        return jdbcTemplate.update("UPDATE category SET name=? WHERE category_id=?",
                category.getName(), category.getCategory_id());
    }

    @Override
    public Optional<Category> findById(Integer id) {
        String sql = "SELECT * FROM category WHERE category_id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql,  BeanPropertyRowMapper.newInstance(Category.class), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM category WHERE category_id=?", id);
    }

    @Override
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * from category", BeanPropertyRowMapper.newInstance(Category.class));
    }
}
