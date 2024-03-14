package org.example.springproject.service.mapper;

import org.example.springproject.controller.dataForm.SushiCreate;
import org.example.springproject.controller.dataForm.SushiUpdate;
import org.example.springproject.model.SushiEntity;
import org.example.springproject.model.SushiTypeEntity;
import org.example.springproject.service.dto.SushiDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SushiMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "sushiType", expression = "java(mapSushiType(dto.getTypeId()))")
    SushiEntity toEntity(SushiCreate dto);
    @Mapping(target = "sushiType", expression = "java(mapSushiType(dto.getTypeId()))")
    SushiEntity toEntity(SushiUpdate dto);

    SushiDto toDto(SushiEntity entity);
    default SushiTypeEntity mapSushiType(UUID sushiTypeId) {
        if (sushiTypeId == null) {
            return null;
        }
        SushiTypeEntity sushiType = new SushiTypeEntity();
        sushiType.setId(sushiTypeId);
        return sushiType;
    }
}
