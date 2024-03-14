package org.example.springproject.service.mapper;

import org.example.springproject.controller.dataForm.SushiTypeCreate;
import org.example.springproject.controller.dataForm.SushiTypeUpdate;
import org.example.springproject.model.SushiEntity;
import org.example.springproject.model.SushiTypeEntity;
import org.example.springproject.service.dto.SushiTypeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SushiTypeMapper {
    @Mapping(target = "id", ignore = true)
    SushiTypeEntity toEntity(SushiTypeCreate dto);
    SushiTypeEntity toEntity(SushiTypeUpdate dto);

    SushiTypeDto toDto(SushiTypeEntity entity);
}
