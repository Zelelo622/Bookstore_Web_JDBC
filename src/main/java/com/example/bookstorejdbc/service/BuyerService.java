package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.data.entity.Buyer;
import com.example.bookstorejdbc.data.mapper.BuyerMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuyerService {
    private final CrudRepository<Buyer> repository;
    private final BuyerMapper mapper;

    public BuyerService(CrudRepository<Buyer> repository, BuyerMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<BuyerDto> getAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public BuyerDto getById(Integer id) {
        return mapper.toDto(repository.findById(id));
    }

    public void addNewBuyer(BuyerDto buyer) {
        repository.save(mapper.toEntity(buyer));
    }

    public void deleteBuyer(Integer id) {
        repository.deleteById(id);
    }

    public void updateBuyer(BuyerDto buyer, Integer id) {
        Buyer oldBuyer = repository.findById(id);
        Buyer newBuyer = mapper.toEntity(buyer);
        newBuyer.setBuyer_id(oldBuyer.getBuyer_id());
        repository.update(newBuyer);
    }
}
