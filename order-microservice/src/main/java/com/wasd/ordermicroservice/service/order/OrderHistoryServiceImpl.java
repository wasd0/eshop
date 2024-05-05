package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderHistoryRequest;
import com.wasd.ordermicroservice.data.order.OrderHistoryResponse;
import com.wasd.ordermicroservice.persistence.order.OrderHistory;
import com.wasd.ordermicroservice.persistence.order.OrderHistoryRepository;
import com.wasd.ordermicroservice.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderHistoryServiceImpl implements OrderHistoryService {
    private final OrderHistoryRepository orderHistoryRepository;

    @Override
    @Transactional
    public OrderHistoryResponse append(OrderHistoryRequest request) {
        OrderHistory orderHistory = OrderMapper.INSTANCE.requestToOrderHistory(request);
        orderHistoryRepository.save(orderHistory);
        return OrderMapper.INSTANCE.orderHistoryToResponse(orderHistory);
    }

    @Override
    public List<OrderHistoryResponse> findAllByOrderId(Long orderId) {
        return orderHistoryRepository.findAllByOrderId(orderId).stream()
                .map(OrderMapper.INSTANCE::orderHistoryToResponse).toList();
    }
}
