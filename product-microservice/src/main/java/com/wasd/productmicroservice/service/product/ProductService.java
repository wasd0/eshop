package com.wasd.productmicroservice.service.product;

import com.wasd.productmicroservice.data.product.ProductRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;

public interface ProductService {

    ProductResponse findById(Long id);

    ProductResponse create(ProductRequest request);
}
