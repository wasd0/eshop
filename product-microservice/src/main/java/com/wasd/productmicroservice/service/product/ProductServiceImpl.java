package com.wasd.productmicroservice.service.product;

import com.wasd.productmicroservice.data.product.ProductCreateRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.persistence.product.Product;
import com.wasd.productmicroservice.persistence.product.ProductRepository;
import com.wasd.productmicroservice.service.warehouseoperation.WarehouseOperationService;
import com.wasd.productmicroservice.util.mapper.ProductMapper;
import com.wasd.productmicroservice.util.mapper.description.WarehouseOperationDescription;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final WarehouseOperationService warehouseOperationService;

    @Override
    public ProductResponse findById(Long id) {
        return productMapper.productToResponse(
            productRepository.findById(id).orElseThrow(()
                -> new NotFoundException(Product.class)));
    }

    @Override
    @Transactional
    public ProductResponse create(ProductCreateRequest request) {
        Product newProduct = productMapper.requestToProduct(request);
        productRepository.save(newProduct);

        WarehouseOperationRequest operationRequest = new WarehouseOperationRequest(
            newProduct.getId(), request.quantity(), WarehouseOperationDescription.CREATE_PRODUCT_DESCRIPTION);

        warehouseOperationService.create(operationRequest);

        return productMapper.productToResponse(newProduct);

    }
}
