package com.wasd.ordermicroservice.persistence.order;


import com.wasd.ordermicroservice.data.order.OrderState;
import jdk.jfr.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private long id;
    private OrderState state;
    private long version;
    private Seller seller;
    private Category category;
    private Brand brand;
}
