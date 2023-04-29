package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        Optional<OrderDto> categoryOptional = service.getOrderById(id);
        if (categoryOptional.isPresent()) {
            OrderDto order = categoryOptional.get();
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else {
            String errorMessage = "Категории не найдена с id " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/order/new")
    public void createOrder(@RequestBody OrderDto order) {
        service.createOrder(order);
    }
}
