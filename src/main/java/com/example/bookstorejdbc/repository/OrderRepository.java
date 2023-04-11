package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.entity.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class OrderRepository {

    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public Order save(Order order) {
        String sql = "INSERT INTO orderb (total_price, buyer_id) VALUES (?, ?)";

        jdbcTemplate.update(sql, new Object[]{order.getTotal_price(), order.getBuyer_id()});

        int orderId = jdbcTemplate.queryForObject("SELECT LASTVAL()", Integer.class);
        addBooksToOrder(order, orderId);

        return order;
    }
    public Order findById(Integer id) {
        String sql = "SELECT * FROM orderb WHERE orderb_id = ?";
        Order order = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Order.class), id);
        order.setBookIds(getBookIdsForOrder(id));
        return order;
    }

    private List<Integer> getBookIdsForOrder(Integer orderId) {
        String sql = "SELECT book_id FROM order_book WHERE orderb_id = ?";
        return jdbcTemplate.queryForList(sql, Integer.class, orderId);
    }

    private void addBooksToOrder(Order order, int orderId) {
        String sql = "INSERT INTO order_book (orderb_id, book_id) VALUES (?, ?)";
        for (Integer bookId : order.getBookIds()) {
            jdbcTemplate.update(sql, new Object[]{orderId, bookId});
        }
    }

    public void deleteById(Integer id) {
        String sql = "DELETE FROM orderb WHERE orderb_id = ?";
        jdbcTemplate.update(sql, id);
    }

    public List<Order> findAll() {
        String sql = "SELECT * FROM orderb";
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
        for (Order order : orders) {
            order.setBookIds(getBookIdsForOrder(order.getOrderb_id()));
        }
        return orders;
    }

    public void addBookToOrder(Integer orderId, Integer bookId) {
        String sql = "INSERT INTO order_book (order_id, book_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, orderId, bookId);
    }

    public void removeBookFromOrder(Integer orderId, Integer bookId) {
        String sql = "DELETE FROM order_book WHERE order_id = ? AND book_id = ?";
        jdbcTemplate.update(sql, orderId, bookId);
    }
}
