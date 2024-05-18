package com.wasd.ordermicroservice.data.order;

import java.util.Set;

public record OrderHistoryRequest(Long orderId, Set<Long> productIndexes, String description) {
}
