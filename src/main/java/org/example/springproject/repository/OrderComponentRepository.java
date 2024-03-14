package org.example.springproject.repository;

import org.example.springproject.model.OrderComponentEntity;
import org.example.springproject.model.OrderComponentPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderComponentRepository extends JpaRepository<OrderComponentEntity, OrderComponentPrimaryKey> {
    List<OrderComponentEntity> findByOrderId(UUID orderId);
}
