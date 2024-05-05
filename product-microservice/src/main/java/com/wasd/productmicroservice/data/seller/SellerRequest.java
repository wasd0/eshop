package com.wasd.productmicroservice.data.seller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record SellerRequest(
        @NotBlank
        String title,
        @NotBlank
        @Length(max = 300)
        String description,
        @NotNull
        @Min(0L)
        Integer tin
) {
}
