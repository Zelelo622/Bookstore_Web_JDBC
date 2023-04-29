package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Buyer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class BuyerRepository implements CrudRepository<Buyer> {
    private final JdbcTemplate jdbcTemplate;

    public BuyerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Buyer buyer) {
        return jdbcTemplate.update("INSERT INTO buyer (first_name, second_name, email, phone) VALUES (?, ?, ?, ?)",
                buyer.getFirst_name(), buyer.getSecond_name(), buyer.getEmail(), buyer.getPhone());
    }

    @Override
    public int update(Buyer buyer) {
        return jdbcTemplate.update("UPDATE buyer SET first_name=?, second_name=?, email=?, phone=? WHERE buyer_id=?",
                buyer.getFirst_name(), buyer.getSecond_name(), buyer.getEmail(), buyer.getPhone(), buyer.getBuyer_id());
    }

    @Override
    public Optional<Buyer> findById(Integer id) {
        String sql = "SELECT * FROM buyer WHERE buyer_id=?";
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Buyer.class), id));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM buyer WHERE buyer_id=?", id);
    }

    @Override
    public List<Buyer> findAll() {
        return jdbcTemplate.query("SELECT * from buyer", BeanPropertyRowMapper.newInstance(Buyer.class));
    }
}
