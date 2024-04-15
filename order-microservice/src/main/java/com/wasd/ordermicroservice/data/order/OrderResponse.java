package com.wasd.ordermicroservice.data.order;

public record OrderResponse(Long id, OrderState state, Long sellerId, Long categoryId, Integer brandId) {
}
