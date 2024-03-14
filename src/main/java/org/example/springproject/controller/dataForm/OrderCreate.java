package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.springproject.model.OrderEntity;

public class OrderCreate {
    @NotNull(message = "Status can't be null!")
    private OrderEntity.OrderStatus status;

    public OrderCreate() {
    }
    public OrderCreate(OrderEntity.OrderStatus status) {
        this.status = status;
    }

    public OrderEntity.OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderEntity.OrderStatus status) {
        this.status = status;
    }
}
