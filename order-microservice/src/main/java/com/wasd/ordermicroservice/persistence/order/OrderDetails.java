package com.wasd.ordermicroservice.persistence.order;

import com.wasd.ordermicroservice.data.order.Money;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
    private Order order;
    private Money price;
    private String title;
    private long customerId;
}
