package org.example.springproject.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "order_component")
public class OrderComponentEntity {
    @EmbeddedId
    private OrderComponentPrimaryKey id;
    @ManyToOne
    @JoinColumn(name = "sushi_id", insertable = false, updatable = false)
    private SushiEntity sushi;
    @ManyToOne
    @JoinColumn(name = "order_id", insertable = false, updatable = false)
    private OrderEntity order;
    private int amount;

    public OrderComponentPrimaryKey getId() {
        return id;
    }

    public void setId(OrderComponentPrimaryKey id) {
        this.id = id;
    }

    public SushiEntity getSushi() {
        return sushi;
    }

    public void setSushi(SushiEntity sushi) {
        this.sushi = sushi;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderComponentEntity{" +
                "id=" + id +
                ", sushi=" + sushi +
                ", order=" + order +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderComponentEntity that)) return false;
        return amount == that.amount && Objects.equals(id, that.id) && Objects.equals(sushi, that.sushi) && Objects.equals(order, that.order);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sushi, order, amount);
    }
}
