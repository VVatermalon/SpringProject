package org.example.springproject.controller.dataForm;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SushiTypeCreate {
    @NotNull(message = "Name can't be null!")
    @NotBlank(message = "Name can't be blank!")
    private String name;

    public SushiTypeCreate() {
    }

    public SushiTypeCreate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
