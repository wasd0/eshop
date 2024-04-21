package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.exception.OrderCreationException;

import java.util.List;

public interface OrderService {
    List<OrderResponse> findAll();

    OrderResponse findById(Long id) throws NotFoundException;

    void create(OrderRequest request) throws OrderCreationException;
}