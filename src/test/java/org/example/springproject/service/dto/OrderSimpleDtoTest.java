package org.example.springproject.service.dto;

import org.example.springproject.model.OrderEntity;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrderSimpleDtoTest {

    @Test
    void testGettersAndSetters_Id() {
        UUID id = UUID.randomUUID();
        OrderSimpleDto orderSimpleDto = new OrderSimpleDto();

        orderSimpleDto.setId(id);

        assertNotNull(orderSimpleDto.getId());
        assertEquals(id, orderSimpleDto.getId());
    }

    @Test
    void testGettersAndSetters_Status() {
        OrderEntity.OrderStatus status = OrderEntity.OrderStatus.IN_PROCESS;
        OrderSimpleDto orderSimpleDto = new OrderSimpleDto();

        orderSimpleDto.setStatus(status);

        assertNotNull(orderSimpleDto.getStatus());
        assertEquals(status, orderSimpleDto.getStatus());
    }

    @Test
    void testGettersAndSetters_TotalPrice() {
        BigDecimal totalPrice = BigDecimal.valueOf(20.5);
        OrderSimpleDto orderSimpleDto = new OrderSimpleDto();

        orderSimpleDto.setTotalPrice(totalPrice);

        assertNotNull(orderSimpleDto.getTotalPrice());
        assertEquals(totalPrice, orderSimpleDto.getTotalPrice());
    }
}
