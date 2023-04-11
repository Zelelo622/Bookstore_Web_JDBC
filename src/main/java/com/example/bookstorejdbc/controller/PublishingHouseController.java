package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.PublishingHouseDto;
import com.example.bookstorejdbc.service.PublishingHouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublishingHouseController {
    private final PublishingHouseService service;

    public PublishingHouseController(PublishingHouseService service) {
        this.service = service;
    }

    @GetMapping("/publishing_house")
    public List<PublishingHouseDto> getAllPublishingHouse() {
        return service.getAll();
    }

    @GetMapping("/publishing_house/{id}")
    public PublishingHouseDto getPublishingHouseById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/publishing_house/delete/{id}")
    public void deletePublishingHouse(@PathVariable Integer id) {
        service.deletePublishingHouse(id);
    }

    @PostMapping("/publishing_house/new")
    public void addPublishingHouse(@RequestBody PublishingHouseDto publishingHouse) {
        service.addNewPublishingHouse(publishingHouse);
    }

    @PutMapping("/publishing_house/edit/{id}")
    public void editPublishingHouse(@RequestBody PublishingHouseDto publishingHouse, @PathVariable Integer id) {
        service.updatePublishingHouse(publishingHouse, id);
    }
}
