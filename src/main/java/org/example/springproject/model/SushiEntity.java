package org.example.springproject.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Entity
@Table(name = "sushi")
public class SushiEntity {
    @Id
    @Column(name = "sushi_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "sushi_name")
    private String name;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private SushiTypeEntity sushiType;
    private BigDecimal price;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_component",
            joinColumns = @JoinColumn(name = "sushi_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<OrderEntity> orders;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SushiTypeEntity getSushiType() {
        return sushiType;
    }

    public void setSushiType(SushiTypeEntity type) {
        this.sushiType = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "SushiEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + sushiType +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", orders=" + orders +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SushiEntity that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(sushiType, that.sushiType) && Objects.equals(price, that.price) && Objects.equals(description, that.description) && Objects.equals(orders, that.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sushiType, price, description, orders);
    }
}
