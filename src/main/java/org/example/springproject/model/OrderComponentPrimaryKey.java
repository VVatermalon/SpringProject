package org.example.springproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;
@Embeddable
public class OrderComponentPrimaryKey implements Serializable {
    @Column(name = "sushi_id")
    private UUID sushi;
    @Column(name = "order_id")
    private UUID order;

    public OrderComponentPrimaryKey(UUID sushi, UUID order) {
        this.sushi = sushi;
        this.order = order;
    }

    public OrderComponentPrimaryKey() {
    }

    public UUID getSushi() {
        return sushi;
    }

    public void setSushi(UUID sushi) {
        this.sushi = sushi;
    }

    public UUID getOrder() {
        return order;
    }

    public void setOrder(UUID order) {
        this.order = order;
    }
}
