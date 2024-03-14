package org.example.springproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrderEntityTest {
    private OrderEntity orderEntity;

    @BeforeEach
    void setUp() {
        orderEntity = new OrderEntity();
    }

    @Test
    void testIdGetterAndSetter() {
        UUID id = UUID.randomUUID();
        orderEntity.setId(id);
        assertEquals(id, orderEntity.getId());
    }

    @Test
    void testStatusGetterAndSetter() {
        OrderEntity.OrderStatus status = OrderEntity.OrderStatus.IN_PROCESS;
        orderEntity.setStatus(status);
        assertEquals(status, orderEntity.getStatus());
    }

    @Test
    void testTotalPriceGetterAndSetter() {
        BigDecimal totalPrice = BigDecimal.valueOf(100);
        orderEntity.setTotalPrice(totalPrice);
        assertEquals(totalPrice, orderEntity.getTotalPrice());
    }

    @Test
    void testComponentsGetterAndSetter() {
        List<SushiEntity> components = new ArrayList<>();
        components.add(new SushiEntity());
        orderEntity.setComponents(components);
        assertEquals(components, orderEntity.getComponents());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        OrderEntity.OrderStatus status = OrderEntity.OrderStatus.IN_PROCESS;
        BigDecimal totalPrice = BigDecimal.valueOf(100);
        List<SushiEntity> components = new ArrayList<>();
        components.add(new SushiEntity());

        OrderEntity orderEntity1 = new OrderEntity();
        orderEntity1.setId(id);
        orderEntity1.setStatus(status);
        orderEntity1.setTotalPrice(totalPrice);
        orderEntity1.setComponents(components);

        OrderEntity orderEntity2 = new OrderEntity();
        orderEntity2.setId(id);
        orderEntity2.setStatus(status);
        orderEntity2.setTotalPrice(totalPrice);
        orderEntity2.setComponents(components);

        assertEquals(orderEntity1, orderEntity2);
        assertEquals(orderEntity1.hashCode(), orderEntity2.hashCode());

        orderEntity2.setId(UUID.randomUUID());
        assertNotEquals(orderEntity1, orderEntity2);
        assertNotEquals(orderEntity1.hashCode(), orderEntity2.hashCode());
    }
}
