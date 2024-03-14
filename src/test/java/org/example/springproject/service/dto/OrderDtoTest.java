package org.example.springproject.service.dto;

import org.example.springproject.model.OrderEntity;
import org.example.springproject.service.dto.OrderDto;
import org.example.springproject.service.dto.SushiSimpleDto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderDtoTest {

    @Test
    void testGettersAndSetters_Id() {
        UUID id = UUID.randomUUID();
        OrderDto orderDto = new OrderDto();

        orderDto.setId(id);

        assertNotNull(orderDto.getId());
        assertEquals(id, orderDto.getId());
    }

    @Test
    void testGettersAndSetters_Status() {
        OrderEntity.OrderStatus status = OrderEntity.OrderStatus.IN_PROCESS;
        OrderDto orderDto = new OrderDto();

        orderDto.setStatus(status);

        assertNotNull(orderDto.getStatus());
        assertEquals(status, orderDto.getStatus());
    }

    @Test
    void testGettersAndSetters_TotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(20.5);
        OrderDto orderDto = new OrderDto();

        orderDto.setTotalPrice(totalPrice);

        assertNotNull(orderDto.getTotalPrice());
        assertEquals(totalPrice, orderDto.getTotalPrice());
    }

    @Test
    void testGettersAndSetters_Components() {
        List<SushiSimpleDto> components = new ArrayList<>();
        OrderDto orderDto = new OrderDto();

        orderDto.setComponents(components);

        assertNotNull(orderDto.getComponents());
        assertEquals(components, orderDto.getComponents());
    }
}
