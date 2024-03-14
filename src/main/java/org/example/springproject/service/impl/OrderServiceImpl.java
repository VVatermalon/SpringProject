package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.OrderEntity;
import org.example.springproject.repository.OrderRepository;
import org.example.springproject.service.OrderService;
import org.example.springproject.service.dto.OrderDto;
import org.example.springproject.service.mapper.OrderMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository repository, OrderMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrderDto findById(UUID id) {
        return mapper.toDto(repository.findById(id).orElseThrow(() -> new NoSuchEntityException("No order entity with such id")));
    }

    @Override
    public List<OrderDto> findAll() {
        List<OrderEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }

    @Override
    public OrderDto save(OrderCreate orderData) {
        return mapper.toDto(repository.save(mapper.toEntity(orderData)));
    }

    @Override
    public OrderDto update(OrderUpdate orderData) {
        return mapper.toDto(repository.save(mapper.toEntity(orderData)));
    }

    @Override
    public OrderDto delete(UUID id) {
        Optional<OrderEntity> optionalOrderEntity = repository.findById(id);
        if (optionalOrderEntity.isPresent()) {
            OrderEntity orderEntity = optionalOrderEntity.get();
            repository.deleteById(id);
            return mapper.toDto(orderEntity);
        } else {
            throw new NoSuchEntityException("No order entity with such id");
        }
    }
}
