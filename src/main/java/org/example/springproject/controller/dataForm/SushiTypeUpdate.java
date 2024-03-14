package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class SushiTypeUpdate {
    @NotNull(message = "Id can't be null!")
    private UUID id;
    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String name;

    public SushiTypeUpdate() {
    }

    public SushiTypeUpdate(UUID id, String name) {
        this.id = id;
        this.name = name;
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
}
