package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.OrderComponentEntity;
import org.example.springproject.model.OrderComponentPrimaryKey;
import org.example.springproject.repository.OrderComponentRepository;
import org.example.springproject.service.dto.OrderComponentDto;
import org.example.springproject.service.mapper.OrderComponentMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderComponentServiceImplTest {

    @Mock
    private OrderComponentRepository repository;

    @Mock
    private OrderComponentMapper mapper;

    @InjectMocks
    private OrderComponentServiceImpl service;

    @Test
    void findByOrderId_ExistingOrderId_ReturnsDtoList() {
        UUID orderId = UUID.randomUUID();
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        when(repository.findByOrderId(orderId)).thenReturn(Collections.singletonList(orderComponentEntity));
        when(mapper.toDto(orderComponentEntity)).thenReturn(orderComponentDto);

        var result = service.findByOrderId(orderId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(orderComponentDto, result.get(0));

        verify(repository, times(1)).findByOrderId(orderId);
        verify(mapper, times(1)).toDto(orderComponentEntity);
    }

    @Test
    void findAll_ReturnsDtoList() {
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        when(repository.findAll()).thenReturn(Collections.singletonList(orderComponentEntity));
        when(mapper.toDto(orderComponentEntity)).thenReturn(orderComponentDto);

        var result = service.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(orderComponentDto, result.get(0));

        verify(repository, times(1)).findAll();
        verify(mapper, times(1)).toDto(orderComponentEntity);
    }

    @Test
    void save_NewOrderComponent_ReturnsDto() {
        OrderComponentForm orderComponentForm = new OrderComponentForm();
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        when(mapper.toEntity(orderComponentForm)).thenReturn(orderComponentEntity);
        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.empty())
                .thenReturn(Optional.of(orderComponentEntity));
        when(repository.save(orderComponentEntity)).thenReturn(orderComponentEntity);
        when(mapper.toDto(orderComponentEntity)).thenReturn(orderComponentDto);

        var result = service.save(orderComponentForm);

        assertNotNull(result);
        assertEquals(orderComponentDto, result);

        verify(repository, times(2)).findById(any(OrderComponentPrimaryKey.class));
        verify(repository, times(1)).save(orderComponentEntity);
        verify(mapper, times(1)).toDto(orderComponentEntity);
    }


    @Test
    void save_ExistingOrderComponent_ThrowsException() {
        OrderComponentForm orderComponentForm = new OrderComponentForm();
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();

        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.of(orderComponentEntity));

        assertThrows(EntityAlreadyExistsException.class, () -> service.save(orderComponentForm));

        verify(repository, times(1)).findById(any(OrderComponentPrimaryKey.class));
        verifyNoMoreInteractions(repository, mapper);
    }

    @Test
    void update_ExistingOrderComponent_ReturnsDto() {
        OrderComponentForm orderComponentForm = new OrderComponentForm();
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        when(mapper.toEntity(orderComponentForm)).thenReturn(orderComponentEntity);
        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.of(orderComponentEntity));
        when(repository.save(orderComponentEntity)).thenReturn(orderComponentEntity);
        when(mapper.toDto(orderComponentEntity)).thenReturn(orderComponentDto);

        var result = service.update(orderComponentForm);

        assertNotNull(result);
        assertEquals(orderComponentDto, result);

        verify(repository, times(1)).save(orderComponentEntity);
        verify(mapper, times(1)).toDto(orderComponentEntity);
    }

    @Test
    void update_NonExistingOrderComponent_ThrowsException() {
        OrderComponentForm orderComponentForm = new OrderComponentForm();

        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.empty());

        assertThrows(EntityAlreadyExistsException.class, () -> service.update(orderComponentForm));

        verify(repository, times(1)).findById(any(OrderComponentPrimaryKey.class));
    }

    @Test
    void delete_ExistingOrderComponent_ReturnsDto() {
        UUID sushiId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        OrderComponentEntity orderComponentEntity = new OrderComponentEntity();
        OrderComponentDto orderComponentDto = new OrderComponentDto();

        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.of(orderComponentEntity));
        doNothing().when(repository).deleteById(any(OrderComponentPrimaryKey.class));
        when(mapper.toDto(orderComponentEntity)).thenReturn(orderComponentDto);

        var result = service.delete(sushiId, orderId);

        assertNotNull(result);
        assertEquals(orderComponentDto, result);

        verify(repository, times(1)).findById(any(OrderComponentPrimaryKey.class));
        verify(repository, times(1)).deleteById(any(OrderComponentPrimaryKey.class));
        verify(mapper, times(1)).toDto(orderComponentEntity);
    }

    @Test
    void delete_NonExistingOrderComponent_ThrowsException() {
        UUID sushiId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        when(repository.findById(any(OrderComponentPrimaryKey.class))).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> service.delete(sushiId, orderId));

        verify(repository, times(1)).findById(any(OrderComponentPrimaryKey.class));
    }
}
