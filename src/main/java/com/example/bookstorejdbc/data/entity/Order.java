package com.example.bookstorejdbc.data.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "orderb")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderb_id")
    private Integer order_id;

    @NotNull
    @Column(name = "total_price")
    private Integer total_price;

    @NotNull
    @Column(name = "buyer_id")
    private Buyer buyer;

    private List<Book> book;
}
