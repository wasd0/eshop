package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.service.warehouseoperation.WarehouseOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/warehouseoperation")
public class WarehouseOperationController {
    private final WarehouseOperationService warehouseOperationService;
    
    @GetMapping
    public List<WarehouseOperationResponse> findAllByProductId(Long productId) {
        return warehouseOperationService.findAllByProductId(productId);
    }
    
    @PostMapping
    public WarehouseOperationResponse create(WarehouseOperationRequest request) {
        return warehouseOperationService.create(request);
    }
}
