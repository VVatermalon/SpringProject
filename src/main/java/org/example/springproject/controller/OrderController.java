package org.example.springproject.controller;

import jakarta.validation.Valid;
import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.service.OrderService;
import org.example.springproject.service.dto.OrderDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/orders")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public OrderDto findById(@PathVariable("id")
                             @org.hibernate.validator.constraints.UUID
                             UUID id) {
        return service.findById(id);
    }

    @GetMapping
    public List<OrderDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public OrderDto save(@Valid
                         @RequestBody
                         OrderCreate orderData) {
        return service.save(orderData);
    }

    @PutMapping
    public OrderDto update(@Valid
                           @RequestBody
                           OrderUpdate orderData) {
        return service.update(orderData);
    }

    @DeleteMapping("/{id}")
    public OrderDto delete(@PathVariable("id")
                           @org.hibernate.validator.constraints.UUID
                           UUID id) {
        return service.delete(id);
    }
}
