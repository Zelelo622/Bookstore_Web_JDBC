package com.example.bookstorejdbc.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    int save(T obj);

    int update(T obj);

    Optional<T> findById(Integer id);

    Integer deleteById(Integer id);

    List<T> findAll();
}
