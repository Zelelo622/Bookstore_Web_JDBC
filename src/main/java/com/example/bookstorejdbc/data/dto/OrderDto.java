package com.example.bookstorejdbc.data.dto;


import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Buyer;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto {

    private Integer totalPrice;
    private Integer buyerId;
    private List<Integer> bookIds;
}

