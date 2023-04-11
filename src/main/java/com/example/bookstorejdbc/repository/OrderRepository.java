package com.example.bookstorejdbc.repository;

import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Order;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class OrderRepository implements CrudRepository<Order> {

    private JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public int save(Order order) {
        return jdbcTemplate.update("INSERT INTO order (total_price, buyer_id) VALUES (?, ?)",
                order.getTotal_price(), order.getBuyer().getBuyer_id());

//        int orderId = jdbcTemplate.queryForObject(
//                "INSERT INTO orderb (total_price, buyer_id) VALUES (?, ?) RETURNING orderb_id",
//                Integer.class, order.getTotal_price(), order.getBuyer().getBuyer_id());
//
//        for (Integer bookId : order.getBookIds()) {
//            jdbcTemplate.update("INSERT INTO book_order (book_id, orderb_id) VALUES (?, ?)",
//                    bookId, orderId);
//        }
//
//        return orderId;
    }

    @Override
    @Transactional
    public int update(Order order) {
        return jdbcTemplate.update("UPDATE order SET total_price=?, buyer_id=? WHERE order_id=?",
                order.getTotal_price(), order.getBuyer().getBuyer_id(), order.getOrder_id());
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(Integer id) {
        Order order = jdbcTemplate.queryForObject("SELECT * FROM order WHERE orderb_id=?",
                BeanPropertyRowMapper.newInstance(Order.class), id);

        return order;

//        Order order = jdbcTemplate.queryForObject("SELECT orderb.orderb_id, orderb.total_price, buyer.first_name, buyer.second_name, " +
//                        "GROUP_CONCAT(DISTINCT book.title ORDER BY book.title ASC SEPARATOR ', ') AS book_titles " +
//                        "FROM orderb " +
//                        "JOIN buyer ON orderb.buyer_id = buyer.buyer_id " +
//                        "LEFT JOIN book_order ON orderb.orderb_id = book_order.orderb_id " +
//                        "LEFT JOIN book ON book_order.book_id = book.book_id " +
//                        "WHERE orderb.orderb_id = ? " +
//                        "GROUP BY orderb.orderb_id " +
//                        "ORDER BY orderb.orderb_id ASC",
//                BeanPropertyRowMapper.newInstance(Order.class), id);
//
//        return order;
    }

    @Transactional(readOnly = true)
    public OrderDto findById1(Integer id) {
        String sql = "SELECT orderb_id, total_price, buyer_id FROM orderb WHERE orderb_id=?";
        OrderDto orderDTO = jdbcTemplate.queryForObject(sql, (resultSet, i) -> {
            OrderDto order = new OrderDto();
            order.setTotalPrice(resultSet.getInt("total_price"));
            order.setBuyerId(resultSet.getInt("buyer_id"));
            return order;
        }, id);

        String sql2 = "SELECT book_id FROM order_book WHERE orderb_id=?";
        List<Integer> bookIds = jdbcTemplate.queryForList(sql2, Integer.class, id);
        orderDTO.setBookIds(bookIds);

        return orderDTO;
    }

    @Override
    @Transactional
    public Integer deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM order WHERE orderb_id=?", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Order> findAll() {
//        return jdbcTemplate.query("SELECT * from orderb", BeanPropertyRowMapper.newInstance(Order.class));
        return jdbcTemplate.query("SELECT orderb.orderb_id, orderb.total_price, buyer.first_name, buyer.second_name, " +
                "GROUP_CONCAT(DISTINCT book.title ORDER BY book.title ASC SEPARATOR ', ') AS book_titles " +
                "FROM orderb " +
                "JOIN buyer ON orderb.buyer_id = buyer.buyer_id " +
                "LEFT JOIN book_order ON orderb.orderb_id = book_order.orderb_id " +
                "LEFT JOIN book ON book_order.book_id = book.book_id " +
                "GROUP BY orderb.orderb_id " +
                "ORDER BY orderb.orderb_id ASC", BeanPropertyRowMapper.newInstance(Order.class));
    }
}
