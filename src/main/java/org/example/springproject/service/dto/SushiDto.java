package org.example.springproject.service.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class SushiDto {
    private UUID id;
    private String name;
    private SushiTypeDto sushiType;
    private BigDecimal price;
    private String description;
    private List<OrderSimpleDto> orders;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SushiTypeDto getSushiType() {
        return sushiType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSushiType(SushiTypeDto sushiType) {
        this.sushiType = sushiType;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<OrderSimpleDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderSimpleDto> orders) {
        this.orders = orders;
    }
}