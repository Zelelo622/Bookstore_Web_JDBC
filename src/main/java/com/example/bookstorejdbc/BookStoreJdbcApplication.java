package com.example.bookstorejdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.bookstorejdbc.data.mapper")
public class BookStoreJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreJdbcApplication.class, args);
    }

}
