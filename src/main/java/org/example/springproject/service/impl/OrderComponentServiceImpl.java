package org.example.springproject.service.impl;

import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.exception.EntityAlreadyExistsException;
import org.example.springproject.exception.NoSuchEntityException;
import org.example.springproject.model.OrderComponentEntity;
import org.example.springproject.model.OrderComponentPrimaryKey;
import org.example.springproject.repository.OrderComponentRepository;
import org.example.springproject.service.OrderComponentService;
import org.example.springproject.service.dto.OrderComponentDto;
import org.example.springproject.service.mapper.OrderComponentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderComponentServiceImpl implements OrderComponentService {
    private final OrderComponentRepository repository;
    private final OrderComponentMapper mapper;

    public OrderComponentServiceImpl(OrderComponentRepository repository, OrderComponentMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<OrderComponentDto> findByOrderId(UUID orderId) {
        List<OrderComponentEntity> entities = repository.findByOrderId(orderId);
        return entities.stream().map(mapper::toDto).toList();
    }

    @Override
    public List<OrderComponentDto> findAll() {
        List<OrderComponentEntity> entities = repository.findAll();
        return entities.stream().map(mapper::toDto).toList();
    }

    @Override
    public OrderComponentDto save(OrderComponentForm orderComponentData) {
        OrderComponentPrimaryKey primaryKey = new OrderComponentPrimaryKey(orderComponentData.getSushiId(), orderComponentData.getOrderId());
        Optional<OrderComponentEntity> optionalOrderComponentEntity = repository.findById(primaryKey);
        if (optionalOrderComponentEntity.isEmpty()) {
            repository.save(mapper.toEntity(orderComponentData));
            return mapper.toDto(repository.findById(new OrderComponentPrimaryKey(orderComponentData.getSushiId(), orderComponentData.getOrderId())).get());
        } else {
            throw new EntityAlreadyExistsException("OrderComponent with such id already exists");
        }
    }

    @Override
    public OrderComponentDto update(OrderComponentForm orderComponentData) {
        OrderComponentPrimaryKey primaryKey = new OrderComponentPrimaryKey(orderComponentData.getSushiId(), orderComponentData.getOrderId());
        Optional<OrderComponentEntity> optionalOrderComponentEntity = repository.findById(primaryKey);
        if (optionalOrderComponentEntity.isPresent()) {
            repository.save(mapper.toEntity(orderComponentData));
            return mapper.toDto(repository.findById(new OrderComponentPrimaryKey(orderComponentData.getSushiId(), orderComponentData.getOrderId())).get());
        } else {
            throw new EntityAlreadyExistsException("No order component entity with such id");
        }
    }

    @Override
    public OrderComponentDto delete(UUID sushiId, UUID orderId) {
        OrderComponentPrimaryKey primaryKey = new OrderComponentPrimaryKey(sushiId, orderId);
        Optional<OrderComponentEntity> optionalOrderComponentEntity = repository.findById(primaryKey);
        if (optionalOrderComponentEntity.isPresent()) {
            OrderComponentEntity orderComponentEntity = optionalOrderComponentEntity.get();
            repository.deleteById(primaryKey);
            return mapper.toDto(orderComponentEntity);
        } else {
            throw new NoSuchEntityException("No order component entity with such id");
        }
    }
}
