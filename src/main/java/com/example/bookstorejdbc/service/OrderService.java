package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.data.mapper.OrderMapper;
import com.example.bookstorejdbc.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository orderRepository, OrderMapper mapper) {
        this.orderRepository = orderRepository;
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
}
