package org.example.springproject.service.dto;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SushiTypeDtoTest {

    @Test
    void testGettersAndSetters_Id() {
        UUID id = UUID.randomUUID();
        SushiTypeDto sushiTypeDto = new SushiTypeDto();

        sushiTypeDto.setId(id);

        assertNotNull(sushiTypeDto.getId());
        assertEquals(id, sushiTypeDto.getId());
    }

    @Test
    void testGettersAndSetters_Name() {
        String name = "Sushi Type Name";
        SushiTypeDto sushiTypeDto = new SushiTypeDto();

        sushiTypeDto.setName(name);

        assertNotNull(sushiTypeDto.getName());
        assertEquals(name, sushiTypeDto.getName());
    }
}
