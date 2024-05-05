package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderHistoryRequest;
import com.wasd.ordermicroservice.data.order.OrderHistoryResponse;

import java.util.List;

public interface OrderHistoryService {
    OrderHistoryResponse append(OrderHistoryRequest request);
    List<OrderHistoryResponse> findAllByOrderId(Long orderId);
}
