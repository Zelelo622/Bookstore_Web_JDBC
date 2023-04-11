package com.example.bookstorejdbc.data.dto;

import lombok.Data;

@Data
public class BookDto {
    private String title;

    private String author;

    private Integer price;

    private Integer category_id;

    private Integer publishing_house_id;
}
