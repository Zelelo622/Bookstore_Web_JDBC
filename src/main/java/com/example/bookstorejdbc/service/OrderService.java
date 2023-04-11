package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.data.mapper.OrderMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import com.example.bookstorejdbc.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderService(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<OrderDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public OrderDto getById(Integer id) {
        return mapper.toDto(repository.findById(id));
    }

    public void addNewOrder(OrderDto order) {
        repository.save(mapper.toEntity(order));
    }

    public void deleteOrder(Integer id) {
        repository.deleteById(id);
    }

    public void updateOrder(OrderDto order, Integer id) {
        Order oldOrder = repository.findById(id);
        Order newOrder = mapper.toEntity(order);
        newOrder.setOrder_id(oldOrder.getOrder_id());
        repository.update(newOrder);
    }

    public OrderDto getOrderById(Integer orderId) {
        OrderDto orderDto = repository.findById1(orderId);
        return orderDto;
    }
}
