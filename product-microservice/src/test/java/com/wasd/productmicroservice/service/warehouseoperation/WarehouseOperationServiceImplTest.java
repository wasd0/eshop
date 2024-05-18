package com.wasd.productmicroservice.service.warehouseoperation;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.exception.persistence.WarehouseOperationCreationException;
import com.wasd.productmicroservice.persistence.product.Product;
import com.wasd.productmicroservice.persistence.product.ProductRepository;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperation;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WarehouseOperationServiceImplTest {
    @InjectMocks
    WarehouseOperationServiceImpl warehouseOperationService;
    @Mock
    WarehouseOperationRepository warehouseOperationRepository;
    @Mock
    ProductRepository productRepository;

    @Test
    void findAllByProductId_whenIncorrectProductId_returnsEmptyList() {
        Long id = 0L;
        when(warehouseOperationRepository.findAllByProductId(id)).thenReturn(new ArrayList<>());
        List<WarehouseOperationResponse> responses = warehouseOperationService.findAllByProductId(id);
        assertEquals(0, responses.size());
    }

    @Test
    void findAllByProductId_whenCorrectProductId_returnsResponseList() {
        Long id = 0L;
        when(warehouseOperationRepository.findAllByProductId(id)).thenReturn(List.of(new WarehouseOperation()));
        List<WarehouseOperationResponse> responses = warehouseOperationService.findAllByProductId(id);
        assertEquals(1, responses.size());
    }

    @Test
    void create_whenRequestIsInvalid_throwsWarehouseOperationCreationException() {
        Long id = 0L;
        WarehouseOperationRequest request = new WarehouseOperationRequest(id, 0, "");
        when(productRepository.getReferenceById(id)).thenThrow(EntityNotFoundException.class);
        assertThrows(WarehouseOperationCreationException.class, () -> warehouseOperationService.create(request));
    }

    @Test
    void create_whenRequestIsValid_returnsResponse() {
        Long id = 0L;
        Product product = new Product();
        product.setId(id);
        WarehouseOperationRequest request = new WarehouseOperationRequest(id, 0, "");
        when(productRepository.getReferenceById(id)).thenReturn(product);
        WarehouseOperationResponse response = warehouseOperationService.create(request);
        assertNotNull(response);
        assertNotNull(response.operationTime());
        assertNotNull(response.productId());
        verify(warehouseOperationRepository, times(1)).save(any());
    }
}