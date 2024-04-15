package com.wasd.ordermicroservice.data.order;

public record OrderRequest(String state, Long sellerId, Long categoryId, Integer brandId) {
}
