package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.SushiEntity;
import org.example.springproject.repository.SushiRepository;
import org.example.springproject.service.SushiService;
import org.example.springproject.service.dto.SushiDto;
import org.example.springproject.service.mapper.SushiMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SushiServiceImpl implements SushiService {
    private final SushiRepository repository;
    private final SushiMapper mapper;

    public SushiServiceImpl(SushiRepository repository, SushiMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
    @Override
    public SushiDto findById(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No sushi entity with such id")));
    }

    @Override
    public List<SushiDto> findAll() {
        List<SushiEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }

    @Override
    public SushiDto save(SushiCreate sushiData) {
        if (repository.existsByName(sushiData.getName()))
            throw new EntityAlreadyExistsException("Sushi with such name already exists");
        return mapper.toDto(repository.save(mapper.toEntity(sushiData)));
    }

    @Override
    public SushiDto update(SushiUpdate sushiData) {
        return mapper.toDto(repository.save(mapper.toEntity(sushiData)));
    }

    @Override
    public SushiDto delete(UUID id) {
        Optional<SushiEntity> optionalSushiEntity = repository.findById(id);
        if (optionalSushiEntity.isPresent()) {
            SushiEntity sushiEntity = optionalSushiEntity.get();
            repository.deleteById(id);
            return mapper.toDto(sushiEntity);
        } else {
            throw new NoSuchEntityException("No sushi entity with such id");
        }
    }

    @Override
    public List<SushiDto> findByType(UUID typeId) {
        List<SushiEntity> entities = repository.findBySushiTypeId(typeId);
        return entities.stream().map(mapper::toDto).toList();
    }
}
