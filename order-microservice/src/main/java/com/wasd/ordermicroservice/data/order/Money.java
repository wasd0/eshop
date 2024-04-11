package com.wasd.ordermicroservice.data.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Money {
    private BigDecimal amount;
    
    public boolean isGreaterOrEqualsThan(Money other) {
        return amount.compareTo(other.amount) >= 0;
    }
}
