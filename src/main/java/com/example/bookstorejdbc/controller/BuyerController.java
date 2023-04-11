package com.example.bookstorejdbc.controller;

import com.example.bookstorejdbc.data.dto.BuyerDto;
import com.example.bookstorejdbc.service.BuyerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuyerController {
    private final BuyerService service;

    public BuyerController(BuyerService service) {
        this.service = service;
    }

    @GetMapping("/buyer")
    public List<BuyerDto> getAllBuyer() {
        return service.getAll();
    }

    @GetMapping("/buyer/{id}")
    public BuyerDto getBuyerById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @DeleteMapping("/buyer/delete/{id}")
    public void deleteBuyer(@PathVariable Integer id) {
        service.deleteBuyer(id);
    }

    @PostMapping("/buyer/new")
    public void addBuyer(@RequestBody BuyerDto buyer) {
        service.addNewBuyer(buyer);
    }

    @PutMapping("/buyer/edit/{id}")
    public void editBuyer(@RequestBody BuyerDto buyer, @PathVariable Integer id) {
        service.updateBuyer(buyer, id);
    }
}
