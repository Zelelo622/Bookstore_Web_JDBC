package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.data.entity.Buyer;
import org.springframework.stereotype.Component;

@Component
public class BuyerMapper {
    public BuyerDto toDto(Buyer entity) {
        BuyerDto dto = new BuyerDto();
        dto.setFirst_name(entity.getFirst_name());
        dto.setSecond_name(entity.getSecond_name());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        return dto;
    }

    public Buyer toEntity(BuyerDto dto) {
        Buyer entity = new Buyer();
        entity.setFirst_name(dto.getFirst_name());
        entity.setSecond_name(dto.getSecond_name());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
    }
}
