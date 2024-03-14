package org.example.springproject.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SushiEntityTest {
    private SushiEntity sushiEntity;

    @BeforeEach
    void setUp() {
        sushiEntity = new SushiEntity();
    }

    @Test
    void testIdGetterAndSetter() {
        UUID id = UUID.randomUUID();
        sushiEntity.setId(id);
        assertEquals(id, sushiEntity.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        String name = "California Roll";
        sushiEntity.setName(name);
        assertEquals(name, sushiEntity.getName());
    }

    @Test
    void testSushiTypeGetterAndSetter() {
        SushiTypeEntity sushiType = new SushiTypeEntity();
        sushiEntity.setSushiType(sushiType);
        assertEquals(sushiType, sushiEntity.getSushiType());
    }

    @Test
    void testPriceGetterAndSetter() {
        BigDecimal price = BigDecimal.valueOf(15.99);
        sushiEntity.setPrice(price);
        assertEquals(price, sushiEntity.getPrice());
    }

    @Test
    void testDescriptionGetterAndSetter() {
        String description = "Delicious sushi roll with crab meat and avocado";
        sushiEntity.setDescription(description);
        assertEquals(description, sushiEntity.getDescription());
    }

    @Test
    void testOrdersGetterAndSetter() {
        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        orders.add(order1);
        orders.add(order2);
        sushiEntity.setOrders(orders);
        assertEquals(orders, sushiEntity.getOrders());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id = UUID.randomUUID();
        String name = "California Roll";
        SushiTypeEntity sushiType = new SushiTypeEntity();
        BigDecimal price = BigDecimal.valueOf(15.99);
        String description = "Delicious sushi roll with crab meat and avocado";
        List<OrderEntity> orders = new ArrayList<>();
        OrderEntity order1 = new OrderEntity();
        OrderEntity order2 = new OrderEntity();
        orders.add(order1);
        orders.add(order2);

        SushiEntity sushiEntity1 = new SushiEntity();
        sushiEntity1.setId(id);
        sushiEntity1.setName(name);
        sushiEntity1.setSushiType(sushiType);
        sushiEntity1.setPrice(price);
        sushiEntity1.setDescription(description);
        sushiEntity1.setOrders(orders);

        SushiEntity sushiEntity2 = new SushiEntity();
        sushiEntity2.setId(id);
        sushiEntity2.setName(name);
        sushiEntity2.setSushiType(sushiType);
        sushiEntity2.setPrice(price);
        sushiEntity2.setDescription(description);
        sushiEntity2.setOrders(orders);

        assertEquals(sushiEntity1, sushiEntity2);
        assertEquals(sushiEntity1.hashCode(), sushiEntity2.hashCode());

        sushiEntity2.setId(UUID.randomUUID());
        assertNotEquals(sushiEntity1, sushiEntity2);
        assertNotEquals(sushiEntity1.hashCode(), sushiEntity2.hashCode());
    }
}
