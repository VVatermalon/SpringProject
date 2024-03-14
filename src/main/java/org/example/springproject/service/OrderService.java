package org.example.springproject.service;

import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.service.dto.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    OrderDto findById(UUID id);

    List<OrderDto> findAll();

    OrderDto save(OrderCreate orderData);

    OrderDto update(OrderUpdate orderData);

    OrderDto delete(UUID id);
}
