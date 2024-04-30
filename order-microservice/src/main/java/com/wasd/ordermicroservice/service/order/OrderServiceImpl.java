package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.OrderCreationException;
import com.wasd.ordermicroservice.persistence.order.Order;
import com.wasd.ordermicroservice.persistence.order.OrderRepository;
import com.wasd.ordermicroservice.util.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(OrderMapper.INSTANCE::orderToResponse).toList();
    }

    @Override
    public OrderResponse findById(Long id) throws NotFoundException {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException(Order.class));
        return OrderMapper.INSTANCE.orderToResponse(order);
    }

    @Transactional
    @Override
    public void create(OrderRequest request) throws OrderCreationException {
        try {
            orderRepository.save(request.sellerId(),
                    request.categoryId(),
                    request.brandId(),
                    request.price(),
                    request.customerId());
        } catch (RuntimeException e) {
            throw new OrderCreationException();
        }
    }
}
