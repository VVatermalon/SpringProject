package org.example.springproject.controller;

import jakarta.validation.Valid;
import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.service.OrderComponentService;
import org.example.springproject.service.dto.OrderComponentDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/orderComponents")
public class OrderComponentController {
    private final OrderComponentService service;

    public OrderComponentController(OrderComponentService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public List<OrderComponentDto> findById(@PathVariable("id")
                                      @org.hibernate.validator.constraints.UUID
                                      UUID id) {
        return service.findByOrderId(id);
    }

    @GetMapping
    public List<OrderComponentDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public OrderComponentDto save(@Valid
                                  @RequestBody
                                  OrderComponentForm orderComponentData) {
        return service.save(orderComponentData);
    }

    @PutMapping
    public OrderComponentDto update(@Valid
                                    @RequestBody
                                    OrderComponentForm orderComponentData) {
        return service.update(orderComponentData);
    }

    @DeleteMapping()
    public OrderComponentDto delete(@org.hibernate.validator.constraints.UUID
                                    @RequestParam("sushiId") UUID sushiId,
                                    @org.hibernate.validator.constraints.UUID
                                    @RequestParam("orderId") UUID orderId) {
        return service.delete(sushiId, orderId);
    }
}