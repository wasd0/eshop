package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.brand.BrandRequest;
import com.wasd.productmicroservice.data.brand.BrandResponse;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.service.brand.BrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("{id}")
    public BrandResponse findById(@Min(0L) @PathVariable Integer id) throws NotFoundException {
        return brandService.findById(id);
    }

    @PostMapping
    public BrandResponse create(@Valid @RequestBody BrandRequest request) {
        return brandService.create(request);
    }
}
