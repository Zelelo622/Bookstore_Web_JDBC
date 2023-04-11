package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.PublishingHouse;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class PublishingHouseRepository implements CrudRepository<PublishingHouse> {

    private JdbcTemplate jdbcTemplate;

    public PublishingHouseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public int save(PublishingHouse publishingHouse) {
        return jdbcTemplate.update("INSERT INTO publishing_house (name) VALUES (?)",
                publishingHouse.getName());
    }

    @Override
    @Transactional
    public int update(PublishingHouse publishingHouse) {
        return jdbcTemplate.update("UPDATE publishing_house SET name=? WHERE publishing_house_id=?",
                publishingHouse.getName(), publishingHouse.getPublishing_house_id());
    }

    @Override
    @Transactional(readOnly = true)
    public PublishingHouse findById(Integer id) {
        PublishingHouse publishingHouse = jdbcTemplate.queryForObject("SELECT * FROM publishing_house WHERE publishing_house_id=?",
                BeanPropertyRowMapper.newInstance(PublishingHouse.class), id);

        return publishingHouse;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM publishing_house WHERE publishing_house_id=?", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PublishingHouse> findAll() {
        return jdbcTemplate.query("SELECT * from publishing_house", BeanPropertyRowMapper.newInstance(PublishingHouse.class));
    }
}
