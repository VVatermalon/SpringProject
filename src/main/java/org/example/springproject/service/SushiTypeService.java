package org.example.springproject.service;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.service.dto.SushiDto;
import org.example.springproject.service.dto.SushiTypeDto;

import java.util.List;
import java.util.UUID;

public interface SushiTypeService {
    SushiTypeDto findById(UUID id);

    List<SushiTypeDto> findAll();

    SushiTypeDto save(SushiTypeCreate sushiTypeData);

    SushiTypeDto update(SushiTypeUpdate sushiTypeData);

    SushiTypeDto delete(UUID id);
}
