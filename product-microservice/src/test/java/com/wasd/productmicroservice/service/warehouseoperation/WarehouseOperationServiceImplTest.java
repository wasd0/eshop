package com.wasd.productmicroservice.service.warehouseoperation;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.exception.persistence.WarehouseOperationCreationException;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperation;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WarehouseOperationServiceImplTest {
    @InjectMocks
    WarehouseOperationServiceImpl warehouseOperationService;
    @Mock
    WarehouseOperationRepository warehouseOperationRepository;
    
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
        WarehouseOperationRequest request = new WarehouseOperationRequest(0L, 0, "");
        assertThrows(WarehouseOperationCreationException.class, () -> warehouseOperationService.create(request));
    }
    
    @Test
    void create_whenRequestIsValid_returnsResponse() {
        WarehouseOperationRequest request = new WarehouseOperationRequest(0L, 0, "");
        WarehouseOperationResponse response = warehouseOperationService.create(request);
        assertNotNull(response);
        assertNotNull(response.operationTime());
        assertNotNull(response.id());
        verify(warehouseOperationRepository, times(1)).save(any());
    }
}