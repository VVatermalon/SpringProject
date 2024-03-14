package org.example.springproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class OrderComponentEntityTest {
    private OrderComponentEntity orderComponentEntity;

    @BeforeEach
    void setUp() {
        orderComponentEntity = new OrderComponentEntity();
    }

    @Test
    void testIdGetterAndSetter() {
        OrderComponentPrimaryKey id = new OrderComponentPrimaryKey(UUID.randomUUID(), UUID.randomUUID());
        orderComponentEntity.setId(id);
        assertEquals(id, orderComponentEntity.getId());
    }

    @Test
    void testSushiGetterAndSetter() {
        SushiEntity sushi = new SushiEntity();
        orderComponentEntity.setSushi(sushi);
        assertEquals(sushi, orderComponentEntity.getSushi());
    }

    @Test
    void testOrderGetterAndSetter() {
        OrderEntity order = new OrderEntity();
        orderComponentEntity.setOrder(order);
        assertEquals(order, orderComponentEntity.getOrder());
    }

    @Test
    void testAmountGetterAndSetter() {
        int amount = 10;
        orderComponentEntity.setAmount(amount);
        assertEquals(amount, orderComponentEntity.getAmount());
    }

    @Test
    void testEqualsAndHashCode() {
        OrderComponentPrimaryKey id = new OrderComponentPrimaryKey(UUID.randomUUID(), UUID.randomUUID());
        SushiEntity sushi = new SushiEntity();
        OrderEntity order = new OrderEntity();
        int amount = 10;

        OrderComponentEntity orderComponentEntity1 = new OrderComponentEntity();
        orderComponentEntity1.setId(id);
        orderComponentEntity1.setSushi(sushi);
        orderComponentEntity1.setOrder(order);
        orderComponentEntity1.setAmount(amount);

        OrderComponentEntity orderComponentEntity2 = new OrderComponentEntity();
        orderComponentEntity2.setId(id);
        orderComponentEntity2.setSushi(sushi);
        orderComponentEntity2.setOrder(order);
        orderComponentEntity2.setAmount(amount);

        assertEquals(orderComponentEntity1, orderComponentEntity2);
        assertEquals(orderComponentEntity1.hashCode(), orderComponentEntity2.hashCode());

        orderComponentEntity2.setId(new OrderComponentPrimaryKey(UUID.randomUUID(), UUID.randomUUID()));
        assertNotEquals(orderComponentEntity1, orderComponentEntity2);
        assertNotEquals(orderComponentEntity1.hashCode(), orderComponentEntity2.hashCode());
    }
}
