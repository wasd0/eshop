package com.wasd.productmicroservice.service.product;

import com.wasd.productmicroservice.data.product.ProductRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.persistence.product.Product;
import com.wasd.productmicroservice.persistence.product.ProductRepository;
import com.wasd.productmicroservice.util.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    ProductServiceImpl productService;
    @Mock
    ProductRepository productRepository;
    @Spy
    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void findById_whenIncorrectId_ThrowsNotFoundException() {
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> productService.findById(id));
    }

    @Test
    void findById_whenCorrectId_returnsResponse() throws NotFoundException {
        Long id = 1L;
        when(productRepository.findById(id)).thenReturn(Optional.of(new Product()));
        ProductResponse result = productService.findById(id);
        Assertions.assertNotNull(result);
    }

    @Test
    void create_withCorrectRequestData_savesAndReturnsResponse() {
        ProductResponse response = productService.create(new ProductRequest("", "", 1L, 1L, 1));
        verify(productRepository, times(1)).save(any());
        Assertions.assertNotNull(response);
    }
}
