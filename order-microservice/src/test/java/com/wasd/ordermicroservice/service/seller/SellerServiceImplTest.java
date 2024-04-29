package com.wasd.ordermicroservice.service.seller;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.persistence.seller.Seller;
import com.wasd.ordermicroservice.persistence.seller.SellerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceImplTest {
    @InjectMocks
    SellerServiceImpl sellerService;
    @Mock
    SellerRepository sellerRepository;

    @Test
    void findById_whenIncorrectId_ThrowsNotFoundException() {
        Long id = 1L;
        when(sellerRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> sellerService.findById(id));
    }

    @Test
    void findById_whenCorrectId_returnsResponse() throws NotFoundException {
        Long id = 1L;
        when(sellerRepository.findById(id)).thenReturn(Optional.of(new Seller(id, "", "", 0, null)));
        SellerResponse result = sellerService.findById(id);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.id());
    }

    @Test
    void create_withCorrectRequestData_savesAndReturnsResponse() {
        String title = "test";
        String description = "test2";
        sellerService.create(new SellerRequest(title, description, 1));
        verify(sellerRepository, times(1)).save(any());
    }
}
