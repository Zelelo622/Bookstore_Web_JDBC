package com.example.bookstorejdbc.data.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "order_book")
public class OrderBook {

    @NotNull
    @Column(name = "orderb_id")
    private Integer orderb_id;

    @NotNull
    @Column(name = "book_id")
    private Integer book_id;
}
