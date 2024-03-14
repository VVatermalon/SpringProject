package org.example.springproject.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SushiTypeEntityTest {

    @Test
    void testGetterAndSetter() {
        SushiTypeEntity sushiTypeEntity = new SushiTypeEntity();
        UUID id = UUID.randomUUID();
        String name = "Test Sushi Type";

        sushiTypeEntity.setId(id);
        sushiTypeEntity.setName(name);

        assertEquals(id, sushiTypeEntity.getId());
        assertEquals(name, sushiTypeEntity.getName());
    }

    @Test
    void testEqualsAndHashCode() {
        UUID id1 = UUID.randomUUID();
        UUID id2 = UUID.randomUUID();
        String name1 = "Test Name";
        String name2 = "Test Name2";

        SushiTypeEntity sushiTypeEntity1 = new SushiTypeEntity();
        sushiTypeEntity1.setId(id1);
        sushiTypeEntity1.setName(name1);

        SushiTypeEntity sushiTypeEntity2 = new SushiTypeEntity();
        sushiTypeEntity2.setId(id1);
        sushiTypeEntity2.setName(name1);

        assertEquals(sushiTypeEntity1, sushiTypeEntity2);
        assertEquals(sushiTypeEntity1.hashCode(), sushiTypeEntity2.hashCode());

        sushiTypeEntity2.setId(id2);
        sushiTypeEntity2.setName(name2);
        assertNotEquals(sushiTypeEntity1, sushiTypeEntity2);
        assertNotEquals(sushiTypeEntity1.hashCode(), sushiTypeEntity2.hashCode());
    }
}
