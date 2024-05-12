package com.wasd.productmicroservice.persistence.warehouseoperation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WarehouseOperationRepository extends JpaRepository<WarehouseOperation, Long> {
    List<WarehouseOperation> findAllByProductId(Long productId);
}
