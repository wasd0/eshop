package com.wasd.productmicroservice.service.product;

import com.wasd.productmicroservice.data.product.ProductRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.persistence.product.Product;
import com.wasd.productmicroservice.persistence.product.ProductRepository;
import com.wasd.productmicroservice.util.mapper.ProductMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    @Override
    public ProductResponse findById(Long id) {
        return productMapper.productToResponse(
            productRepository.findById(id).orElseThrow(()
                -> new NotFoundException(Product.class)));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductRequest request) {
        Product newProduct = productMapper.requestToProduct(request);
        productRepository.save(newProduct);
        return productMapper.productToResponse(newProduct);

    }
}
