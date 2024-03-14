package org.example.springproject.repository;

import org.example.springproject.model.SushiTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SushiTypeRepository extends JpaRepository<SushiTypeEntity, UUID> {
    boolean existsByName(String name);
}