package com.wasd.ordermicroservice.data.order;

import java.math.BigDecimal;

public record OrderRequest(Long sellerId, Long categoryId, Integer brandId, BigDecimal price,
                           Long customerId) {
}
