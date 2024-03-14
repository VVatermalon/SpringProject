package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class SushiUpdate {
    @NotNull(message = "Id can't be null!")
    private UUID id;
    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String name;
    @NotNull(message = "Type id can't be null!")
    private UUID typeId;
    @NotNull(message = "Price can't be null!")
    @Digits(message = "Price must contain from 0 to 3 digits before ',' and 2 after!", integer = 3, fraction = 2)
    private BigDecimal price;
    @NotNull(message = "Description can't be null!")
    @NotBlank(message = "Description can't be blank!")
    private String description;

    public SushiUpdate() {
    }

    public SushiUpdate(UUID id, String name, UUID typeId, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.typeId = typeId;
        this.price = price;
        this.description = description;
    }

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

    public UUID getTypeId() {
        return typeId;
    }

    public void setTypeId(UUID typeId) {
        this.typeId = typeId;
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
}
