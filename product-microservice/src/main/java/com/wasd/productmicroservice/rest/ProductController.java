package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.product.ProductCreateRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.service.product.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("{id}")
    public ProductResponse findById(@Min(0L) @PathVariable Long id) throws NotFoundException {
        return productService.findById(id);
    }

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductCreateRequest request) throws AlreadyExistsException {
        return productService.create(request);
    }
}
