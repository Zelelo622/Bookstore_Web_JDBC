package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class PublishingHouseRepository implements CrudRepository<PublishingHouse> {

    private JdbcTemplate jdbcTemplate;

    public PublishingHouseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(PublishingHouse publishingHouse) {
        return jdbcTemplate.update("INSERT INTO publishing_house (name) VALUES (?)",
                publishingHouse.getName());
    }

    @Override
    public int update(PublishingHouse publishingHouse) {
        return jdbcTemplate.update("UPDATE publishing_house SET name=? WHERE publishing_house_id=?",
                publishingHouse.getName(), publishingHouse.getPublishing_house_id());
    }

    @Override
    public Optional<PublishingHouse> findById(Integer id) {
        String sql = "SELECT * FROM publishing_house WHERE publishing_house_id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(PublishingHouse.class), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM publishing_house WHERE publishing_house_id=?", id);
    }

    @Override
    public List<PublishingHouse> findAll() {
        return jdbcTemplate.query("SELECT * from publishing_house", BeanPropertyRowMapper.newInstance(PublishingHouse.class));
    }
}
