package com.wasd.productmicroservice.brand;

import com.wasd.productmicroservice.data.brand.BrandRequest;
import com.wasd.productmicroservice.data.brand.BrandResponse;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.persistence.brand.Brand;
import com.wasd.productmicroservice.persistence.brand.BrandRepository;
import com.wasd.productmicroservice.service.brand.BrandServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BrandServiceImplTest {
    @InjectMocks
    BrandServiceImpl brandService;
    @Mock
    BrandRepository brandRepository;

    @Test
    void findById_whenIncorrectId_ThrowsBrandNotFoundException() {
        Integer id = 1;
        when(brandRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> brandService.findById(id));
    }

    @Test
    void findById_whenCorrectId_returnsResponse() throws NotFoundException {
        Integer id = 1;
        when(brandRepository.findById(id)).thenReturn(Optional.of(new Brand(1, "test", "", null)));
        BrandResponse result = brandService.findById(id);
        Assertions.assertNotNull(result);
    }

    @Test
    void create_withCorrectRequestData_savesAndReturnsResponse() {
        String title = "test";
        String description = "test2";
        brandService.create(new BrandRequest(title, description));
        verify(brandRepository, times(1)).save(any());
    }
}