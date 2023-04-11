package com.example.bookstorejdbc.data.mapper;

import com.example.bookstorejdbc.data.dto.PublishingHouseDto;
import com.example.bookstorejdbc.data.entity.PublishingHouse;
import org.springframework.stereotype.Component;

@Component
public class PublishingHouseMapper {
    public PublishingHouseDto toDto(PublishingHouse entity) {
        PublishingHouseDto dto = new PublishingHouseDto();
        dto.setName(entity.getName());
        return dto;
    }

    public PublishingHouse toEntity(PublishingHouseDto dto) {
        PublishingHouse entity = new PublishingHouse();
        entity.setName(dto.getName());
        return entity;
    }
}
