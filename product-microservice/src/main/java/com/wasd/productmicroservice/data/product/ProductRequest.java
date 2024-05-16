package com.wasd.productmicroservice.data.product;

public record ProductRequest(String title, String description, Long sellerId, Long categoryId,
                             Integer brandId) {
}
