package com.wasd.productmicroservice.rest;

import com.wasd.eshopcommon.exception.NotFoundException;
import com.wasd.productmicroservice.data.product.ProductCreateRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.service.product.ProductService;
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
@RequestMapping("api/v1/products")
public class ProductController {
	private final ProductService productService;

	@GetMapping("{id}")
	public ProductResponse findById(@Min(0L) @PathVariable Long id) throws NotFoundException {
		return productService.findById(id);
	}

	@PostMapping
	public ProductResponse create(@Valid @RequestBody ProductCreateRequest request) {
		return productService.create(request);
	}
}
