package org.example.springproject.service.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.example.springproject.model.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderDto {
    private UUID id;
    private OrderEntity.OrderStatus status;
    private BigDecimal totalPrice;
    @JsonManagedReference
    private List<SushiSimpleDto> components;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderEntity.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderEntity.OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SushiSimpleDto> getComponents() {
        return components;
    }

    public void setComponents(List<SushiSimpleDto> components) {
        this.components = components;
    }
}
