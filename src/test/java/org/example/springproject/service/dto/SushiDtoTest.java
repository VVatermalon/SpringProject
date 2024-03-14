package org.example.springproject.service.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SushiDtoTest {

    @Test
    void testGettersAndSetters_Id() {
        UUID id = UUID.randomUUID();
        SushiDto sushiDto = new SushiDto();

        sushiDto.setId(id);

        assertNotNull(sushiDto.getId());
        assertEquals(id, sushiDto.getId());
    }

    @Test
    void testGettersAndSetters_Name() {
        String name = "Sushi Name";
        SushiDto sushiDto = new SushiDto();

        sushiDto.setName(name);

        assertNotNull(sushiDto.getName());
        assertEquals(name, sushiDto.getName());
    }

    @Test
    void testGettersAndSetters_SushiType() {
        SushiTypeDto sushiType = new SushiTypeDto();
        SushiDto sushiDto = new SushiDto();

        sushiDto.setSushiType(sushiType);

        assertNotNull(sushiDto.getSushiType());
        assertEquals(sushiType, sushiDto.getSushiType());
    }

    @Test
    void testGettersAndSetters_Price() {
        BigDecimal price = BigDecimal.valueOf(15.99);
        SushiDto sushiDto = new SushiDto();

        sushiDto.setPrice(price);

        assertNotNull(sushiDto.getPrice());
        assertEquals(price, sushiDto.getPrice());
    }

    @Test
    void testGettersAndSetters_Description() {
        String description = "Delicious sushi";
        SushiDto sushiDto = new SushiDto();

        sushiDto.setDescription(description);

        assertNotNull(sushiDto.getDescription());
        assertEquals(description, sushiDto.getDescription());
    }

    @Test
    void testGettersAndSetters_Orders() {
        List<OrderSimpleDto> orders = new ArrayList<>();
        SushiDto sushiDto = new SushiDto();

        sushiDto.setOrders(orders);

        assertNotNull(sushiDto.getOrders());
        assertEquals(orders, sushiDto.getOrders());
    }
}
