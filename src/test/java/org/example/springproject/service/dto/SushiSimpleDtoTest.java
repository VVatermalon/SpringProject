package org.example.springproject.service.dto;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SushiSimpleDtoTest {

    @Test
    void testGettersAndSetters_Id() {
        UUID id = UUID.randomUUID();
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();

        sushiSimpleDto.setId(id);

        assertNotNull(sushiSimpleDto.getId());
        assertEquals(id, sushiSimpleDto.getId());
    }

    @Test
    void testGettersAndSetters_Name() {
        String name = "Sushi Name";
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();

        sushiSimpleDto.setName(name);

        assertNotNull(sushiSimpleDto.getName());
        assertEquals(name, sushiSimpleDto.getName());
    }

    @Test
    void testGettersAndSetters_SushiType() {
        SushiTypeDto sushiType = new SushiTypeDto();
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();

        sushiSimpleDto.setSushiType(sushiType);

        assertNotNull(sushiSimpleDto.getSushiType());
        assertEquals(sushiType, sushiSimpleDto.getSushiType());
    }

    @Test
    void testGettersAndSetters_Price() {
        BigDecimal price = BigDecimal.valueOf(15.99);
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();

        sushiSimpleDto.setPrice(price);

        assertNotNull(sushiSimpleDto.getPrice());
        assertEquals(price, sushiSimpleDto.getPrice());
    }

    @Test
    void testGettersAndSetters_Description() {
        String description = "Delicious sushi";
        SushiSimpleDto sushiSimpleDto = new SushiSimpleDto();

        sushiSimpleDto.setDescription(description);

        assertNotNull(sushiSimpleDto.getDescription());
        assertEquals(description, sushiSimpleDto.getDescription());
    }
}
