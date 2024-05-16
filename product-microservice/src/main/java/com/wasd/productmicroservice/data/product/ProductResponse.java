package com.wasd.productmicroservice.data.product;

public record ProductResponse(Long id, Long sellerId, Long categoryId, Integer brandId) {
}
