package org.example.springproject.service.mapper;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.model.SushiEntity;
import org.example.springproject.model.SushiTypeEntity;
import org.example.springproject.service.dto.SushiDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SushiMapperTest {

    private final SushiMapper sushiMapper = Mappers.getMapper(SushiMapper.class);

    @Test
    void testToEntityWithSushiCreateDto() {
        SushiCreate sushiCreate = new SushiCreate("California Roll", UUID.randomUUID(), null, "Delicious sushi roll");
        SushiEntity sushiEntity = sushiMapper.toEntity(sushiCreate);
        assertEquals(sushiCreate.getName(), sushiEntity.getName());
        assertEquals(sushiCreate.getDescription(), sushiEntity.getDescription());
        assertEquals(sushiCreate.getTypeId(), sushiEntity.getSushiType().getId());
    }

    @Test
    void testToEntityWithSushiUpdateDto() {
        SushiUpdate sushiUpdate = new SushiUpdate(UUID.randomUUID(), "California Roll", UUID.randomUUID(), null, "Delicious sushi roll");
        SushiEntity sushiEntity = sushiMapper.toEntity(sushiUpdate);
        assertEquals(sushiUpdate.getId(), sushiEntity.getId());
        assertEquals(sushiUpdate.getName(), sushiEntity.getName());
        assertEquals(sushiUpdate.getDescription(), sushiEntity.getDescription());
        assertEquals(sushiUpdate.getTypeId(), sushiEntity.getSushiType().getId());
    }

    @Test
    void testToDto() {
        SushiEntity sushiEntity = new SushiEntity();
        sushiEntity.setId(UUID.randomUUID());
        sushiEntity.setName("California Roll");
        sushiEntity.setDescription("Delicious sushi roll");
        SushiTypeEntity sushiType = new SushiTypeEntity();
        sushiType.setId(UUID.randomUUID());
        sushiEntity.setSushiType(sushiType);
        SushiDto sushiDto = sushiMapper.toDto(sushiEntity);
        assertEquals(sushiEntity.getId(), sushiDto.getId());
        assertEquals(sushiEntity.getName(), sushiDto.getName());
        assertEquals(sushiEntity.getDescription(), sushiDto.getDescription());
        assertEquals(sushiEntity.getSushiType().getId(), sushiDto.getSushiType().getId());
    }

    @Test
    void testMapSushiTypeWithNullSushiTypeId() {
        UUID sushiTypeId = null;
        SushiTypeEntity sushiType = sushiMapper.mapSushiType(sushiTypeId);
        assertNull(sushiType);
    }

    @Test
    void testMapSushiTypeWithValidSushiTypeId() {
        UUID sushiTypeId = UUID.randomUUID();
        SushiTypeEntity sushiType = sushiMapper.mapSushiType(sushiTypeId);
        assertEquals(sushiTypeId, sushiType.getId());
    }
}
