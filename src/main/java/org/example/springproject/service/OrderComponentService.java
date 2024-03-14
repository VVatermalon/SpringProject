package org.example.springproject.service;

import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.service.dto.OrderComponentDto;

import java.util.List;
import java.util.UUID;

public interface OrderComponentService {
    List<OrderComponentDto> findByOrderId(UUID orderId);

    List<OrderComponentDto> findAll();

    OrderComponentDto save(OrderComponentForm orderComponentData);

    OrderComponentDto update(OrderComponentForm orderComponentData);

    OrderComponentDto delete(UUID sushiId, UUID orderId);
}
