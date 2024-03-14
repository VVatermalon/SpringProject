package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class OrderComponentForm {
    @NotNull(message = "Id can't be null!")
    private UUID sushiId;
    @NotNull(message = "Id can't be null!")
    private UUID orderId;
    @NotNull(message = "Amount can't be null!")
    @Min(value = 1, message = "Amount must be greater than 0!")
    @Max(value = 50, message = "Amount must be less than 50!")
    private int amount;

    public OrderComponentForm() {
    }

    public OrderComponentForm(UUID sushiId, UUID orderId, int amount) {
        this.sushiId = sushiId;
        this.orderId = orderId;
        this.amount = amount;
    }

    public UUID getSushiId() {
        return sushiId;
    }

    public void setSushiId(UUID sushiId) {
        this.sushiId = sushiId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
