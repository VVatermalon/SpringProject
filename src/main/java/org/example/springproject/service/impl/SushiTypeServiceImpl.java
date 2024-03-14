package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.SushiTypeEntity;
import org.example.springproject.repository.SushiTypeRepository;
import org.example.springproject.service.SushiTypeService;
import org.example.springproject.service.dto.SushiTypeDto;
import org.example.springproject.service.mapper.SushiTypeMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SushiTypeServiceImpl implements SushiTypeService {
    private final SushiTypeRepository repository;
    private final SushiTypeMapper mapper;

    public SushiTypeServiceImpl(SushiTypeRepository repository, SushiTypeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public SushiTypeDto findById(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No sushi entity with such id")));
    }

    @Override
    public List<SushiTypeDto> findAll() {
        List<SushiTypeEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }

    @Override
    public SushiTypeDto save(SushiTypeCreate sushiData) {
        if (repository.existsByName(sushiData.getName()))
            throw new EntityAlreadyExistsException("Sushi with such name already exists");
        return mapper.toDto(repository.save(mapper.toEntity(sushiData)));
    }

    @Override
    public SushiTypeDto update(SushiTypeUpdate sushiData) {
        return mapper.toDto(repository.save(mapper.toEntity(sushiData)));
    }

    @Override
    public SushiTypeDto delete(UUID id) {
        Optional<SushiTypeEntity> optionalSushiEntity = repository.findById(id);
        if (optionalSushiEntity.isPresent()) {
            SushiTypeEntity sushiEntity = optionalSushiEntity.get();
            repository.deleteById(id);
            return mapper.toDto(sushiEntity);
        } else {
            throw new NoSuchEntityException("No sushi entity with such id");
        }
    }
}
