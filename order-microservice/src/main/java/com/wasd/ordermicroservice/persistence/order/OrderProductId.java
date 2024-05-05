package com.wasd.ordermicroservice.persistence.order;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProductId implements Serializable {
    private Long productId;
    private Long orderId;

}
