package com.wasd.ordermicroservice.persistence.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderHistoryRepository extends JpaRepository<OrderHistory, Long> {
    @Query("from OrderHistory oh where oh.order.id = :orderId")
    List<OrderHistory> findAllByOrderId(@Param("orderId") Long orderId);
}
