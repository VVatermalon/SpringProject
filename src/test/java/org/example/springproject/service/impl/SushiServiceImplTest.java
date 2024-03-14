package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.SushiEntity;
import org.example.springproject.repository.SushiRepository;
import org.example.springproject.service.dto.SushiDto;
import org.example.springproject.service.mapper.SushiMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SushiServiceImplTest {

    @Mock
    private SushiRepository repository;

    @Mock
    private SushiMapper mapper;

    @InjectMocks
    private SushiServiceImpl service;

    @Test
    void findById_ExistingId_ReturnsDto() {
        UUID id = UUID.randomUUID();
        SushiEntity sushiEntity = new SushiEntity();
        SushiDto sushiDto = new SushiDto();
        sushiEntity.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(sushiEntity));
        when(mapper.toDto(sushiEntity)).thenReturn(sushiDto);

        SushiDto result = service.findById(id);

        assertNotNull(result);
        assertEquals(sushiDto, result);

        verify(repository, times(1)).findById(id);
        verify(mapper, times(1)).toDto(sushiEntity);
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
        List<SushiEntity> sushiEntities = new ArrayList<>();
        List<SushiDto> sushiDtos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            SushiEntity sushiEntity = new SushiEntity();
            SushiDto sushiDto = new SushiDto();
            sushiEntity.setId(UUID.randomUUID());
            sushiEntities.add(sushiEntity);
            sushiDtos.add(sushiDto);
        }

        when(repository.findAll()).thenReturn(sushiEntities);
        when(mapper.toDto(any(SushiEntity.class))).thenReturn(sushiDtos.get(0), sushiDtos.get(1), sushiDtos.get(2));

        List<SushiDto> result = service.findAll();

        assertNotNull(result);
        assertEquals(sushiDtos.size(), result.size());
        assertEquals(sushiDtos, result);

        verify(repository, times(1)).findAll();
        verify(mapper, times(3)).toDto(any(SushiEntity.class));
    }

    @Test
    void save_NewSushi_ReturnsDto() {
        SushiCreate sushiCreate = new SushiCreate("California Roll", UUID.randomUUID(), BigDecimal.valueOf(10.99), "Delicious sushi roll");

        SushiEntity sushiEntity = new SushiEntity();
        SushiDto sushiDto = new SushiDto();

        when(repository.existsByName(sushiCreate.getName())).thenReturn(false);
        when(mapper.toEntity(sushiCreate)).thenReturn(sushiEntity);
        when(repository.save(sushiEntity)).thenReturn(sushiEntity);
        when(mapper.toDto(sushiEntity)).thenReturn(sushiDto);

        SushiDto result = service.save(sushiCreate);

        assertNotNull(result);
        assertEquals(sushiDto, result);

        verify(repository, times(1)).existsByName(sushiCreate.getName());
        verify(mapper, times(1)).toEntity(sushiCreate);
        verify(repository, times(1)).save(sushiEntity);
        verify(mapper, times(1)).toDto(sushiEntity);
    }

    @Test
    void save_ExistingSushi_ThrowsException() {
        SushiCreate sushiCreate = new SushiCreate("California Roll", UUID.randomUUID(), BigDecimal.valueOf(10.99), "Delicious sushi roll");

        when(repository.existsByName(sushiCreate.getName())).thenReturn(true);

        assertThrows(EntityAlreadyExistsException.class, () -> service.save(sushiCreate));

        verify(repository, times(1)).existsByName(sushiCreate.getName());
        verifyNoMoreInteractions(mapper, repository);
    }

    @Test
    void update_ValidSushi_ReturnsDto() {
        SushiUpdate sushiUpdate = new SushiUpdate(UUID.randomUUID(), "California Roll", UUID.randomUUID(), BigDecimal.valueOf(10.99), "Delicious sushi roll");

        SushiEntity sushiEntity = new SushiEntity();
        SushiDto sushiDto = new SushiDto();

        when(mapper.toEntity(sushiUpdate)).thenReturn(sushiEntity);
        when(repository.save(sushiEntity)).thenReturn(sushiEntity);
        when(mapper.toDto(sushiEntity)).thenReturn(sushiDto);

        SushiDto result = service.update(sushiUpdate);

        assertNotNull(result);
        assertEquals(sushiDto, result);

        verify(mapper, times(1)).toEntity(sushiUpdate);
        verify(repository, times(1)).save(sushiEntity);
        verify(mapper, times(1)).toDto(sushiEntity);
    }

    @Test
    void delete_ExistingSushi_ReturnsDto() {
        UUID id = UUID.randomUUID();

        SushiEntity sushiEntity = new SushiEntity();
        SushiDto sushiDto = new SushiDto();

        when(repository.findById(id)).thenReturn(Optional.of(sushiEntity));
        when(mapper.toDto(sushiEntity)).thenReturn(sushiDto);

        SushiDto result = service.delete(id);

        assertNotNull(result);
        assertEquals(sushiDto, result);

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).deleteById(id);
        verify(mapper, times(1)).toDto(sushiEntity);
    }

    @Test
    void delete_NonExistingSushi_ThrowsException() {
        UUID id = UUID.randomUUID();

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> service.delete(id));

        verify(repository, times(1)).findById(id);
        verifyNoMoreInteractions(mapper, repository);
    }
}
