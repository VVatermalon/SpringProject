package org.example.springproject.service;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.service.dto.SushiDto;

import java.util.List;
import java.util.UUID;

public interface SushiService {
    SushiDto findById(UUID id);

    List<SushiDto> findAll();

    SushiDto save(SushiCreate sushiData);

    SushiDto update(SushiUpdate sushiData);

    SushiDto delete(UUID id);

    List<SushiDto> findByType(UUID typeId);
}
