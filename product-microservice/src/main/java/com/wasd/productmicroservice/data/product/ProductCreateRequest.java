package com.wasd.productmicroservice.data.product;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProductCreateRequest(
    @NotNull
    String title,
    @NotNull
    String description,
    @Min(0L)
    Integer quantity,
    @Min(0L)
    Long sellerId,
    @Min(0L)
    Long categoryId,
    @Min(0L)
    Integer brandId
) {
}
