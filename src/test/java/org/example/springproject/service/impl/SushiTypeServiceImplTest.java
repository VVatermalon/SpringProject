package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.SushiTypeEntity;
import org.example.springproject.repository.SushiTypeRepository;
import org.example.springproject.service.dto.SushiTypeDto;
import org.example.springproject.service.mapper.SushiTypeMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SushiTypeServiceImplTest {

    @Mock
    private SushiTypeRepository repository;

    @Mock
    private SushiTypeMapper mapper;

    @InjectMocks
    private SushiTypeServiceImpl service;

    @Test
    void findById_ExistingId_ReturnsDto() {
        UUID id = UUID.randomUUID();
        SushiTypeEntity entity = new SushiTypeEntity();
        SushiTypeDto dto = new SushiTypeDto();
        entity.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        SushiTypeDto result = service.findById(id);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> service.findById(id));

        verify(repository, times(1)).findById(id);
        verifyNoInteractions(mapper);
    }

    @Test
    void findAll_ReturnsListOfDto() {
        List<SushiTypeEntity> entities = new ArrayList<>();
        List<SushiTypeDto> dtos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            SushiTypeEntity entity = new SushiTypeEntity();
            SushiTypeDto dto = new SushiTypeDto();
            entity.setId(UUID.randomUUID());
            entities.add(entity);
            dtos.add(dto);
        }

        when(repository.findAll()).thenReturn(entities);
        when(mapper.toDto(any(SushiTypeEntity.class))).thenReturn(dtos.get(0), dtos.get(1), dtos.get(2));

        List<SushiTypeDto> result = service.findAll();

        assertNotNull(result);
        assertEquals(dtos.size(), result.size());
        assertEquals(dtos, result);

        verify(repository, times(1)).findAll();
        verify(mapper, times(3)).toDto(any(SushiTypeEntity.class));
    }

    @Test
    void save_NewSushiType_ReturnsDto() {
        SushiTypeCreate create = new SushiTypeCreate("Nigiri");

        SushiTypeEntity entity = new SushiTypeEntity();
        SushiTypeDto dto = new SushiTypeDto();

        when(repository.existsByName(create.getName())).thenReturn(false);
        when(mapper.toEntity(create)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        SushiTypeDto result = service.save(create);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(repository, times(1)).existsByName(create.getName());
        verify(mapper, times(1)).toEntity(create);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    void save_ExistingSushiType_ThrowsException() {
        SushiTypeCreate create = new SushiTypeCreate("Nigiri");

        when(repository.existsByName(create.getName())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, () -> service.save(create));

        verify(repository, times(1)).existsByName(create.getName());
        verifyNoMoreInteractions(mapper, repository);
    }

    @Test
    void update_ValidSushiType_ReturnsDto() {
        SushiTypeUpdate update = new SushiTypeUpdate(UUID.randomUUID(), "Nigiri");

        SushiTypeEntity entity = new SushiTypeEntity();
        SushiTypeDto dto = new SushiTypeDto();

        when(mapper.toEntity(update)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(entity);
        when(mapper.toDto(entity)).thenReturn(dto);

        SushiTypeDto result = service.update(update);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(mapper, times(1)).toEntity(update);
        verify(repository, times(1)).save(entity);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    void delete_ExistingSushiType_ReturnsDto() {
        UUID id = UUID.randomUUID();

        SushiTypeEntity entity = new SushiTypeEntity();
        SushiTypeDto dto = new SushiTypeDto();

        when(repository.findById(id)).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        SushiTypeDto result = service.delete(id);

        assertNotNull(result);
        assertEquals(dto, result);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
        verify(mapper, times(1)).toDto(entity);
    }

    @Test
    void delete_NonExistingSushiType_ThrowsException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> service.delete(id));

        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(mapper, repository);
    }
}
