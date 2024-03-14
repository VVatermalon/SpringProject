package org.example.springproject.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    public enum OrderStatus {
        IN_PROCESS, NEED_CONFIRMATION, CONFIRMED, CANCELLED;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
    private OrderStatus status;
    @Column(name = "total_price")
    private BigDecimal totalPrice = BigDecimal.ZERO;
    @ManyToMany(mappedBy = "orders", fetch = FetchType.EAGER)
    private List<SushiEntity> components;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SushiEntity> getComponents() {
        return components;
    }

    public void setComponents(List<SushiEntity> components) {
        this.components = components;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "id=" + id +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                ", components=" + components +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity that)) return false;
        return Objects.equals(id, that.id) && status == that.status && Objects.equals(totalPrice, that.totalPrice) && Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, totalPrice, components);
    }
}
