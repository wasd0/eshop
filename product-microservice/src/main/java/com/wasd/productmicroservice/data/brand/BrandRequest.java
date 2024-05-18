package com.wasd.productmicroservice.data.brand;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record BrandRequest(@NotBlank
                           @Length(max = 100)
                           String title,
                           String description) {
}
