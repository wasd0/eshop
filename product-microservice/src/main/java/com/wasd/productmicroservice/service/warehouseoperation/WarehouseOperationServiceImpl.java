package com.wasd.productmicroservice.service.warehouseoperation;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.exception.persistence.WarehouseOperationCreationException;
import com.wasd.productmicroservice.persistence.product.Product;
import com.wasd.productmicroservice.persistence.product.ProductRepository;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperation;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperationRepository;
import com.wasd.productmicroservice.util.mapper.WarehouseOperationMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseOperationServiceImpl implements WarehouseOperationService {
    private final WarehouseOperationRepository warehouseOperationRepository;
    private final ProductRepository productRepository;

    @Override
    public List<WarehouseOperationResponse> findAllByProductId(Long productId) {
        return warehouseOperationRepository.findAllByProductId(productId).stream()
            .map(WarehouseOperationMapper.INSTANCE::warehouseOperationToResponse).toList();
    }

    @Override
    @Transactional
    public WarehouseOperationResponse create(WarehouseOperationRequest request) throws WarehouseOperationCreationException {
        Product product;
        try {
            product = productRepository.getReferenceById(request.productId());

            WarehouseOperation warehouseOperation = WarehouseOperationMapper.INSTANCE
                .requestToWarehouseOperation(request);

            warehouseOperation.setOperationTime(Instant.now());
            warehouseOperation.setProduct(product);
            warehouseOperationRepository.save(warehouseOperation);

            return WarehouseOperationMapper.INSTANCE.warehouseOperationToResponse(warehouseOperation);
        } catch (RuntimeException e) {
            throw new WarehouseOperationCreationException(
                String.format("Incorrect product with id '%s'", request.productId()));
        }

    }
}
