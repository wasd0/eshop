package com.wasd.productmicroservice.data.category;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CategoryRequest(
        @NotBlank
        @Length(max = 100)
        String title,
        @Length(min = 1, max = 100)
        String parentTitle
) {
}
