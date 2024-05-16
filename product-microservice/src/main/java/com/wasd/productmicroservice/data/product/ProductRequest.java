package com.wasd.productmicroservice.data.product;

public record ProductRequest(Long sellerId, Long categoryId, Integer brandId) {
}
