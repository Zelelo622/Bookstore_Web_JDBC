package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.BookDto;
import com.example.bookstorejdbc.data.dto.OrderDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Buyer;
import com.example.bookstorejdbc.data.entity.Order;
import com.example.bookstorejdbc.repository.BookRepository;
import com.example.bookstorejdbc.repository.BuyerRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final BuyerRepository buyerRepository;
    private final BookRepository bookRepository;

    public OrderMapper(BuyerRepository buyerRepository, BookRepository bookRepository) {
        this.buyerRepository = buyerRepository;
        this.bookRepository = bookRepository;
    }

    public OrderDto toDto(Order entity) {
        OrderDto dto = new OrderDto();
        dto.setTotalPrice(entity.getTotal_price());
        dto.setBuyerId(entity.getBuyer().getBuyer_id());
        List<Integer> bookIds = entity.getBook().stream().map(Book::getBook_id).collect(Collectors.toList());
        dto.setBookIds(bookIds);
        return dto;
    }

    public Order toEntity(OrderDto dto) {
        Order entity = new Order();
        entity.setTotal_price(dto.getTotalPrice());
        entity.setBuyer(buyerRepository.findById(dto.getBuyerId()));
        entity.setBook(bookRepository.getAllById(dto.getBookIds()));
        return entity;
    }
}

