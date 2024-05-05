package com.wasd.ordermicroservice.persistence.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders_products")

public class OrderProduct {
    @EmbeddedId
    private OrderProductId orderProductId;
    @MapsId("orderId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
