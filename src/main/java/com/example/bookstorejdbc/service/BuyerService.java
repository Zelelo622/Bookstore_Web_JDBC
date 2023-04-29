package com.example.bookstorejdbc.service;

import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.data.entity.Book;
import com.example.bookstorejdbc.data.entity.Buyer;
import com.example.bookstorejdbc.data.mapper.BuyerMapper;
import com.example.bookstorejdbc.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<BuyerDto> getById(Integer id) {
        return repository.findById(id).map(mapper::toDto);
    }

    public void addNewBuyer(BuyerDto buyer) {
        repository.save(mapper.toEntity(buyer));
    }

    public void deleteBuyer(Integer id) {
        Optional<Buyer> buyerOptional = repository.findById(id);
        if (buyerOptional.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Покупатель с id " + id + " не найдена");
        }
    }

    public void updateBuyer(BuyerDto buyer, Integer id) {
        Buyer entity = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Покупатель с id " + id + " не найден"));
        entity.setFirst_name(buyer.getFirst_name());
        entity.setSecond_name(buyer.getSecond_name());
        entity.setPhone(buyer.getPhone());
        entity.setEmail(buyer.getEmail());
        repository.update(entity);
    }
}
