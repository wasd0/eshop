package com.wasd.ordermicroservice.data.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderRequest(
        @NotNull
        @Min(0L)
        Long sellerId,
        @NotNull
        @Min(0L)
        Long categoryId,
        @NotNull
        @Min(0L)
        Integer brandId,
        @NotNull
        @Min(0L)
        BigDecimal price,
        @NotNull
        @Min(0L)
        Long customerId
) {
}
