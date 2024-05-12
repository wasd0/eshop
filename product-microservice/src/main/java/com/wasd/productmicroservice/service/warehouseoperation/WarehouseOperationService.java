package com.wasd.productmicroservice.service.warehouseoperation;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.exception.persistence.WarehouseOperationCreationException;

import java.util.List;

public interface WarehouseOperationService {
    List<WarehouseOperationResponse> findAllByProductId(Long productId);

        WarehouseOperationResponse create(WarehouseOperationRequest request) throws WarehouseOperationCreationException;
}
