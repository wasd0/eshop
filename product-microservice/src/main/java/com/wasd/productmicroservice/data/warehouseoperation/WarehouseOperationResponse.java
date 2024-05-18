package com.wasd.productmicroservice.data.warehouseoperation;

import java.time.Instant;

public record WarehouseOperationResponse(Long id, Long productId, int quantity, String description,
                                         Instant operationTime) {
}
