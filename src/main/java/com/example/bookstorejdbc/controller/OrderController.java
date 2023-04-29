package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/order")
    public List<OrderDto> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrderById(@PathVariable Integer id) {
        return service.getOrderById(id);
    }

    @PostMapping("/order/new")
    public void createOrder(@RequestBody OrderDto order) {
        service.createOrder(order);
    }
}
