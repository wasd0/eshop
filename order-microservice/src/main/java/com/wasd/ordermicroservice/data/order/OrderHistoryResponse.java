package com.wasd.ordermicroservice.data.order;

import java.time.Instant;
import java.util.List;

public record OrderHistoryResponse(Long id, Long orderId, List<Long> productIndexes, String description,
                                   Instant operationTime) {
}
