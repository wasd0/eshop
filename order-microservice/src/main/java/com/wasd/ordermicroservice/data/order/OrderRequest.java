package com.wasd.ordermicroservice.data.order;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @NotNull
        @Min(0L)
        BigDecimal price,
        @NotNull
        @Min(0L)
        Long customerId,
        @NotNull
        List<Long> products
) {
}
