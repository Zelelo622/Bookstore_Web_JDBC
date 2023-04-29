package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.PublishingHouseDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Category;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import com.example.bookstorejdbc.data.mapper.PublishingHouseMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublishingHouseService {
    private final CrudRepository<PublishingHouse> repository;
    private final PublishingHouseMapper mapper;

    public PublishingHouseService(CrudRepository<PublishingHouse> repository, PublishingHouseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<PublishingHouseDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Optional<PublishingHouseDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewPublishingHouse(PublishingHouseDto publishingHouse) {
        repository.save(mapper.toEntity(publishingHouse));
    }

    public void deletePublishingHouse(Integer id) {
        Optional<PublishingHouse> publishingHouseOptional = repository.findById(id);
        if (publishingHouseOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Книга с id " + id + " не найдена");
        }
    }

    public void updatePublishingHouse(PublishingHouseDto publishingHouse, Integer id) {
        PublishingHouse entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Издание с id " + id + " не найдено"));
        entity.setName(publishingHouse.getName());
        repository.update(entity);
    }
}
