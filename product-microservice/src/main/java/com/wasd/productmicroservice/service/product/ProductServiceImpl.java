package com.wasd.productmicroservice.service.product;

import com.wasd.productmicroservice.data.product.ProductRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    @Override
    public ProductResponse findById(Long id) {
        return null;
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        return null;
    }
}
