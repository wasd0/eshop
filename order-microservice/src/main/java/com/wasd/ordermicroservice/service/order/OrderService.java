package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.OrderCreationException;

import java.util.List;

public interface 
OrderService {
    List<OrderResponse> findAll();

    OrderResponse findById(Long id) throws NotFoundException;

    OrderResponse create(OrderRequest request) throws OrderCreationException;
}