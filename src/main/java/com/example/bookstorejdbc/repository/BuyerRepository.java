package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Buyer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BuyerRepository implements CrudRepository<Buyer> {
    private final JdbcTemplate jdbcTemplate;

    public BuyerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public int save(Buyer buyer) {
        return jdbcTemplate.update("INSERT INTO buyer (first_name, second_name, email, phone) VALUES (?, ?, ?, ?)",
                buyer.getFirst_name(), buyer.getSecond_name(), buyer.getEmail(), buyer.getPhone());
    }

    @Override
    @Transactional
    public int update(Buyer buyer) {
        return jdbcTemplate.update("UPDATE buyer SET first_name=?, second_name=?, email=?, phone=? WHERE buyer_id=?",
                buyer.getFirst_name(), buyer.getSecond_name(), buyer.getEmail(), buyer.getPhone(), buyer.getBuyer_id());
    }

    @Override
    @Transactional(readOnly = true)
    public Buyer findById(Integer id) {
        Buyer buyer = jdbcTemplate.queryForObject("SELECT * FROM buyer WHERE buyer_id=?",
                BeanPropertyRowMapper.newInstance(Buyer.class), id);

        return buyer;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM buyer WHERE buyer_id=?", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Buyer> findAll() {
        return jdbcTemplate.query("SELECT * from buyer", BeanPropertyRowMapper.newInstance(Buyer.class));
    }
}
