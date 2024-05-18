package com.wasd.ordermicroservice.persistence.order;

import com.wasd.ordermicroservice.data.order.Money;
import com.wasd.ordermicroservice.data.order.OrderState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "amount", column = @Column(name = "price", nullable = false))
    private Money price = new Money();
    @CreationTimestamp
    private Instant createdOn = Instant.now();
    @Column(name = "customer_id", nullable = false)
    private Long customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "state", length = 50, nullable = false)
    private OrderState state = OrderState.PENDING;
}
