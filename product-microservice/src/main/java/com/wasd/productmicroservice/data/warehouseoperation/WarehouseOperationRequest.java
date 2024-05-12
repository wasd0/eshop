package com.wasd.productmicroservice.data.warehouseoperation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record WarehouseOperationRequest(
    @NotNull
    Long productId,
    @NotNull
    int quantity,
    @NotNull
    @NotBlank
    @Length(max = 300)
    String description
) {
}
