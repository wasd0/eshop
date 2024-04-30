package com.wasd.ordermicroservice.data.order;

import java.time.Instant;

public record OrderResponse(Long id, Money price, Instant createdOn, Long customerId) {
}
