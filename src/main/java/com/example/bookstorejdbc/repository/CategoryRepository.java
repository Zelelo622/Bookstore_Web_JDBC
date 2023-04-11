package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Category;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CategoryRepository implements CrudRepository<Category> {

    private JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public int save(Category category) {
        return jdbcTemplate.update("INSERT INTO category (name) VALUES (?)",
                category.getName());
    }

    @Override
    @Transactional
    public int update(Category category) {
        return jdbcTemplate.update("UPDATE category SET name=? WHERE category_id=?",
                category.getName(), category.getCategory_id());
    }

    @Override
    @Transactional(readOnly = true)
    public Category findById(Integer id) {
        Category category = jdbcTemplate.queryForObject("SELECT * FROM category WHERE category_id=?",
                BeanPropertyRowMapper.newInstance(Category.class), id);

        return category;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM category WHERE category_id=?", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Category> findAll() {
        return jdbcTemplate.query("SELECT * from category", BeanPropertyRowMapper.newInstance(Category.class));
    }
}
