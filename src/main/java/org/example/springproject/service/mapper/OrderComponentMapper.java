package org.example.springproject.service.mapper;

import org.example.springproject.controller.dataForm.OrderComponentForm;
import org.example.springproject.model.OrderComponentEntity;
import org.example.springproject.service.dto.OrderComponentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderComponentMapper {
    @Mapping(target = "id.sushi", source = "dto.sushiId")
    @Mapping(target = "id.order", source = "dto.orderId")
    OrderComponentEntity toEntity(OrderComponentForm dto);

    OrderComponentDto toDto(OrderComponentEntity entity);
}
