package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.repository.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {
    public OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setTotalPrice(entity.getTotal_price());
        dto.setBuyerId(entity.getBuyer_id());
        dto.setBookIds(entity.getBookIds());
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setTotal_price(dto.getTotalPrice());
        entity.setBuyer_id(dto.getBuyerId());
        entity.setBookIds(dto.getBookIds());
        return entity;
    }
}

