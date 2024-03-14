package org.example.springproject.controller;

import jakarta.validation.Valid;
import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.service.SushiService;
import org.example.springproject.service.dto.SushiDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Validated
@RequestMapping("/sushi")
public class SushiController {
    private final SushiService service;

    public SushiController(SushiService service) {
        this.service = service;
    }

    @GetMapping(value = "/{id}")
    public SushiDto findById(@PathVariable("id")
                             @org.hibernate.validator.constraints.UUID
                             UUID id) {
        return service.findById(id);
    }

    @GetMapping(value = "/findByType/{id}")
    public List<SushiDto> findByType(@PathVariable("id")
                                     @org.hibernate.validator.constraints.UUID
                                     UUID id) {
        return service.findByType(id);
    }

    @GetMapping
    public List<SushiDto> findAll() {
        return service.findAll();
    }

    @PostMapping
    public SushiDto save(@Valid
                         @RequestBody
                         SushiCreate sushiData) {
        return service.save(sushiData);
    }

    @PutMapping
    public SushiDto update(@Valid
                           @RequestBody
                           SushiUpdate sushiData) {
        return service.update(sushiData);
    }

    @DeleteMapping("/{id}")
    public SushiDto delete(@PathVariable("id")
                           @org.hibernate.validator.constraints.UUID
                           UUID id) {
        return service.delete(id);
    }
}