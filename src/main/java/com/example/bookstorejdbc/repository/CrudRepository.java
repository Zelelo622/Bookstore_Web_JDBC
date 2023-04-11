package com.example.bookstorejdbc.repository;

import java.util.List;

public interface CrudRepository<T> {
    int save(T obj);

    int update(T obj);

    T findById(Integer id);

    Integer deleteById(Integer id);

    List<T> findAll();
}
