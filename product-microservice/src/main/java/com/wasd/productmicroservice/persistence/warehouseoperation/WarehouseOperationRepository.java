package com.wasd.productmicroservice.persistence.warehouseoperation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseOperationRepository extends JpaRepository<WarehouseOperation, Long> {
    List<WarehouseOperation> findAllByProductId(Long productId);

	@Query("select sum(wo.quantity) from WarehouseOperation wo where wo.product.id = :productId")
	Integer getTotalQuantityByProductId(@Param("productId") Long productId);
}
