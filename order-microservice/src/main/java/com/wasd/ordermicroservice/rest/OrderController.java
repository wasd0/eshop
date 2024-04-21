package com.wasd.ordermicroservice.rest;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.exception.OrderCreationException;
import com.wasd.ordermicroservice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> findAll() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable Long id) throws NotFoundException {
        return orderService.findById(id);
    }

    @PostMapping
    public void create(@RequestBody OrderRequest request) throws OrderCreationException {
        orderService.create(request);
    }
}
