package org.example.springproject.service.mapper;

import org.example.springproject.controller.dataForm.OrderCreate;
import org.example.springproject.controller.dataForm.OrderUpdate;
import org.example.springproject.model.OrderEntity;
import org.example.springproject.service.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    OrderEntity toEntity(OrderCreate dto);

    OrderEntity toEntity(OrderUpdate dto);

    OrderDto toDto(OrderEntity entity);
}

