package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.service.warehouseoperation.WarehouseOperationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@GetMapping("{productId}/quantity")
    public Integer getQuantityByProductId(@Min(0) @PathVariable Long productId) {
        return warehouseOperationService.getTotalQuantityByProductId(productId);
    }
}
