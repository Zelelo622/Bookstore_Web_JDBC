package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.data.mapper.OrderMapper;
import com.example.bookstorejdbc.repository.BookRepository;
import com.example.bookstorejdbc.repository.CrudRepository;
import com.example.bookstorejdbc.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository orderRepository, BookRepository bookRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrderDto getOrderById(Integer id) {
        return mapper.toDto(orderRepository.findById(id));
    }

    public void createOrder(OrderDto order) {
        orderRepository.save(mapper.toEntity(order));
    }

    public void updateOrder(Integer id, OrderDto order) {
        Order ent = mapper.toEntity(order);
        ent.setOrderb_id(id);
        orderRepository.save(ent);
    }

    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    public void addBookToOrder(Integer orderId, Integer bookId) {
        Order order = orderRepository.findById(orderId);
        Book book = bookRepository.findById(bookId);
        if (order != null && book != null) {
            order.getBookIds().add(bookId);
            orderRepository.addBookToOrder(orderId, bookId);
        }
    }

    public void removeBookFromOrder(Integer orderId, Integer bookId) {
        Order order = orderRepository.findById(orderId);
        Book book = bookRepository.findById(bookId);
        if (order != null && book != null) {
            order.getBookIds().remove(bookId);
            orderRepository.removeBookFromOrder(orderId, bookId);
        }
    }
}
