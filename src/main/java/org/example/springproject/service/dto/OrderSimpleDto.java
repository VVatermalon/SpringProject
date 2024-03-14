package org.example.springproject.service.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.example.springproject.model.OrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderSimpleDto {
    private UUID id;
    private OrderEntity.OrderStatus status;
    private BigDecimal totalPrice;

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
}
