package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.PublishingHouseDto;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import com.example.bookstorejdbc.data.mapper.PublishingHouseMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public PublishingHouseDto getById(Integer id) {
        return mapper.toDto(repository.findById(id));
    }

    public void addNewPublishingHouse(PublishingHouseDto publishingHouse) {
        repository.save(mapper.toEntity(publishingHouse));
    }

    public void deletePublishingHouse(Integer id) {
        repository.deleteById(id);
    }

    public void updatePublishingHouse(PublishingHouseDto publishingHouse, Integer id) {
        PublishingHouse oldPublishingHouse = repository.findById(id);
        PublishingHouse newPublishingHouse = mapper.toEntity(publishingHouse);
        newPublishingHouse.setPublishing_house_id(oldPublishingHouse.getPublishing_house_id());
        repository.update(newPublishingHouse);
    }
}
