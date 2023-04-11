package com.example.bookstorejdbc.data.dto;

import lombok.Data;

@Data
public class OrderBookDto {
    private Integer orderId;
    private Integer bookId;
    private Integer quantity;
}
