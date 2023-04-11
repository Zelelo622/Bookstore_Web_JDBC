package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.service.CategoryService;
import com.example.bookstorejdbc.service.OrderService;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/order/{id}")
    public void updateOrder(@PathVariable Integer id, @RequestBody OrderDto order) {
        service.updateOrder(id, order);
    }

    @DeleteMapping("/order/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        service.deleteOrder(id);
    }

    @PostMapping("/order/{orderId}/books/{bookId}")
    public void addBookToOrder(@PathVariable Integer orderId, @PathVariable Integer bookId) {
        service.addBookToOrder(orderId, bookId);
    }

    @DeleteMapping("/order/{orderId}/books/{bookId}")
    public void removeBookFromOrder(@PathVariable Integer orderId, @PathVariable Integer bookId) {
        service.removeBookFromOrder(orderId, bookId);
    }
}
