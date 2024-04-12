package com.wasd.ordermicroservice.persistence.order;


import com.wasd.ordermicroservice.data.order.OrderState;
import com.wasd.ordermicroservice.persistence.brand.Brand;
import com.wasd.ordermicroservice.persistence.category.ProductCategory;
import com.wasd.ordermicroservice.persistence.seller.Seller;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private OrderState state;
    private Long version;
    private Seller seller;
    private ProductCategory productCategory;
    private Brand brand;
}
