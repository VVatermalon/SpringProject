package org.example.springproject.controller;
import jakarta.validation.Valid;
import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.service.SushiTypeService;
import org.example.springproject.service.dto.SushiTypeDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/sushiType")
public class SushiTypeController {
    private final SushiTypeService service;

    public SushiTypeController(SushiTypeService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public SushiTypeDto findById(@PathVariable("id")
                             @org.hibernate.validator.constraints.UUID
                             UUID id) {
        return service.findById(id);
    }

    @GetMapping
    public List<SushiTypeDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public SushiTypeDto save(@Valid
                         @RequestBody
                         SushiTypeCreate sushiData) {
        return service.save(sushiData);
    }

    @PutMapping
    public SushiTypeDto update(@Valid
                           @RequestBody
                           SushiTypeUpdate sushiData) {
        return service.update(sushiData);
    }

    @DeleteMapping("/{id}")
    public SushiTypeDto delete(@PathVariable("id")
                           @org.hibernate.validator.constraints.UUID
                           UUID id) {
        return service.delete(id);
    }
}