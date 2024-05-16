package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.service.warehouseoperation.WarehouseOperationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/warehouseoperations")
public class WarehouseOperationController {
    private final WarehouseOperationService warehouseOperationService;

    @GetMapping("{productId}")
    public List<WarehouseOperationResponse> findAllByProductId(@Min(0) @PathVariable Long productId) {
        return warehouseOperationService.findAllByProductId(productId);
    }

    @PostMapping
    public WarehouseOperationResponse create(@Valid @RequestBody WarehouseOperationRequest request) {
        return warehouseOperationService.create(request);
    }
}
