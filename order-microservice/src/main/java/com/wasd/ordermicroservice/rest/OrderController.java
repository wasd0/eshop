package com.wasd.ordermicroservice.rest;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.OrderCreationException;
import com.wasd.ordermicroservice.service.order.OrderService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
    public OrderResponse findById(@Min(0L) @PathVariable Long id) throws NotFoundException {
        return orderService.findById(id);
    }

    @PostMapping
    public void create(@Valid @RequestBody OrderRequest request) throws OrderCreationException {
        orderService.create(request);
    }
}
