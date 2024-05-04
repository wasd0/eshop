package com.wasd.ordermicroservice.data.order;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    private BigDecimal amount = BigDecimal.ZERO;

    public boolean isGreaterOrEqualsThan(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
