package org.example.springproject.repository;

import org.example.springproject.model.SushiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface SushiRepository extends JpaRepository<SushiEntity, UUID> {
    boolean existsByName(String name);
    List<SushiEntity> findBySushiTypeId(UUID sushiTypeId);
}
