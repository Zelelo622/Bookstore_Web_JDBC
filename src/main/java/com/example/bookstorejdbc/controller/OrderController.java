package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.CategoryDto;
import com.example.bookstorejdbc.data.dto.OrderDto;
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
    public List<OrderDto> getAllOrder() {
        return service.getAll();
    }

//    @GetMapping("/order/{id}")
//    public OrderDto getOrderById(@PathVariable Integer id) {
//        return service.getById(id);
//    }

    @DeleteMapping("/order/delete/{id}")
    public void deleteOrder(@PathVariable Integer id) {
        service.deleteOrder(id);
    }

    @PostMapping("/order/new")
    public void addOrder(@RequestBody OrderDto order) {
        service.addNewOrder(order);
    }

    @PutMapping("/order/edit/{id}")
    public void editOrder(@RequestBody OrderDto category, @PathVariable Integer id) {
        service.updateOrder(category, id);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrderById1(@PathVariable("id") Integer id) {
        OrderDto orderDto = service.getOrderById(id);
        if (orderDto == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(orderDto);
        }
    }
}
