package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.OrderEntity;
import org.example.springproject.repository.OrderRepository;
import org.example.springproject.service.dto.OrderDto;
import org.example.springproject.service.mapper.OrderMapper;
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
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void findById_ExistingId_ReturnsDto() {
        UUID id = UUID.randomUUID();
        OrderEntity orderEntity = new OrderEntity();
        OrderDto orderDto = new OrderDto();
        orderEntity.setId(id);

        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.findById(id);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderRepository, times(1)).findById(id);
        verify(orderMapper, times(1)).toDto(orderEntity);
    }

    @Test
    void findById_NonExistingId_ThrowsException() {
        UUID id = UUID.randomUUID();

        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> orderService.findById(id));

        verify(orderRepository, times(1)).findById(id);
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }

    @Test
    void findAll_ReturnsListOfDto() {
        List<OrderEntity> orderEntities = new ArrayList<>();
        List<OrderDto> orderDtos = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            OrderEntity orderEntity = new OrderEntity();
            OrderDto orderDto = new OrderDto();
            orderEntity.setId(UUID.randomUUID());
            orderEntities.add(orderEntity);
            orderDtos.add(orderDto);
        }

        when(orderRepository.findAll()).thenReturn(orderEntities);
        when(orderMapper.toDto(any(OrderEntity.class))).thenReturn(orderDtos.get(0), orderDtos.get(1), orderDtos.get(2));

        List<OrderDto> result = orderService.findAll();

        assertNotNull(result);
        assertEquals(orderDtos.size(), result.size());
        assertEquals(orderDtos, result);

        verify(orderRepository, times(1)).findAll();
        verify(orderMapper, times(3)).toDto(any(OrderEntity.class));
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }

    @Test
    void save_NewOrder_ReturnsDto() {
        OrderCreate orderCreate = new OrderCreate();

        OrderEntity orderEntity = new OrderEntity();
        OrderDto orderDto = new OrderDto();

        when(orderMapper.toEntity(orderCreate)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.save(orderCreate);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderMapper, times(1)).toEntity(orderCreate);
        verify(orderRepository, times(1)).save(orderEntity);
        verify(orderMapper, times(1)).toDto(orderEntity);
        verifyNoMoreInteractions(orderMapper, orderRepository);
    }

    @Test
    void update_ValidOrder_ReturnsDto() {
        OrderUpdate orderUpdate = new OrderUpdate();

        OrderEntity orderEntity = new OrderEntity();
        OrderDto orderDto = new OrderDto();

        when(orderMapper.toEntity(orderUpdate)).thenReturn(orderEntity);
        when(orderRepository.save(orderEntity)).thenReturn(orderEntity);
        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.update(orderUpdate);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderMapper, times(1)).toEntity(orderUpdate);
        verify(orderRepository, times(1)).save(orderEntity);
        verify(orderMapper, times(1)).toDto(orderEntity);
        verifyNoMoreInteractions(orderMapper, orderRepository);
    }

    @Test
    void delete_ExistingOrder_ReturnsDto() {
        UUID id = UUID.randomUUID();

        OrderEntity orderEntity = new OrderEntity();
        OrderDto orderDto = new OrderDto();

        when(orderRepository.findById(id)).thenReturn(Optional.of(orderEntity));
        when(orderMapper.toDto(orderEntity)).thenReturn(orderDto);

        OrderDto result = orderService.delete(id);

        assertNotNull(result);
        assertEquals(orderDto, result);

        verify(orderRepository, times(1)).findById(id);
        verify(orderRepository, times(1)).deleteById(id);
        verify(orderMapper, times(1)).toDto(orderEntity);
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }

    @Test
    void delete_NonExistingOrder_ThrowsException() {
        UUID id = UUID.randomUUID();

        when(orderRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchEntityException.class, () -> orderService.delete(id));

        verify(orderRepository, times(1)).findById(id);
        verifyNoMoreInteractions(orderRepository, orderMapper);
    }
}
