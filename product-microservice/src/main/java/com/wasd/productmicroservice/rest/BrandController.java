package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.brand.BrandRequest;
import com.wasd.productmicroservice.data.brand.BrandResponse;
import com.wasd.productmicroservice.service.brand.BrandService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/brands")
public class BrandController {
    private final BrandService brandService;

    @GetMapping("{id}")
    public BrandResponse findById(@Min(0L) @PathVariable Integer id) {
        return brandService.findById(id);
    }

    @PostMapping
    public BrandResponse create(@Valid @RequestBody BrandRequest request) {
        return brandService.create(request);
    }
}
