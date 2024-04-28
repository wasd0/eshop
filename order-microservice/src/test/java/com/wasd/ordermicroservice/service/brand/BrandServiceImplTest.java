package com.wasd.ordermicroservice.service.brand;

import com.wasd.ordermicroservice.data.brand.BrandRequest;
import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.persistence.brand.Brand;
import com.wasd.ordermicroservice.persistence.brand.BrandRepository;
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