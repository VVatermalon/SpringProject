package org.example.springproject.service.dto;

import java.util.UUID;

public class SushiTypeDto {
    private UUID id;
    private String name;

    public SushiTypeDto(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public SushiTypeDto(String name) {
        this.name = name;
    }
    public SushiTypeDto() {}

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

