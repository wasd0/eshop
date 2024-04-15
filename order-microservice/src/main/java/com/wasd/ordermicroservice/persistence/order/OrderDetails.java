package com.wasd.ordermicroservice.persistence.order;

import com.wasd.ordermicroservice.data.order.Money;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_details")
public class OrderDetails {
    @Id
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price", nullable = false))
    private Money price;
    @Column(name = "title", length = 100, nullable = false)
    private String title;
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Order order;
}
