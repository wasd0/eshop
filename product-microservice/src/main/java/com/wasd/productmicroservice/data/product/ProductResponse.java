package com.wasd.productmicroservice.data.product;

public record ProductResponse(String title, String description, Long id, Long sellerId, Long categoryId,
                              Integer brandId) {
}
