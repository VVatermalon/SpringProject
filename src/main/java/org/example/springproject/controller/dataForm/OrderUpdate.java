package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.springproject.model.OrderEntity;

import java.util.UUID;

public class OrderUpdate {
    @NotNull(message = "Id can't be null!")
    private UUID id;
    @NotNull(message = "Status can't be null!")
    private OrderEntity.OrderStatus status;

    public OrderUpdate() {
    }

    public OrderUpdate(UUID id, OrderEntity.OrderStatus status) {
        this.id = id;
        this.status = status;
    }

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
}
