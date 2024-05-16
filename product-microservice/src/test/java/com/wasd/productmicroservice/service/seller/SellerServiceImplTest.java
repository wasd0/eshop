package com.wasd.productmicroservice.service.seller;

import com.wasd.productmicroservice.data.seller.SellerRequest;
import com.wasd.productmicroservice.data.seller.SellerResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.exception.persistence.SellerCreationException;
import com.wasd.productmicroservice.persistence.seller.Seller;
import com.wasd.productmicroservice.persistence.seller.SellerRepository;
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
    void create_withCorrectRequestData_savesAndReturnsResponse() throws AlreadyExistsException {
        String title = "test";
        String description = "test2";
        sellerService.create(new SellerRequest(title, description, 1));
        verify(sellerRepository, times(1)).save(any());
    }

    @Test
    void create_whenSellerByTinAlreadyExists_throwsAlreadyExistsException() {
        String title = "test";
        String description = "test2";
        Integer tin = 1;

        when(sellerRepository.existsByTin(tin)).thenReturn(true);
        Assertions.assertThrows(AlreadyExistsException.class,
                () -> sellerService.create(new SellerRequest(title, description, tin)));

        verify(sellerRepository, times(0)).save(any());
    }

    @Test
    void create_whenTinLessThanZero_throwsSellerCreationException() {
        String title = "test";
        String description = "test2";
        Integer tin = -1;

        Assertions.assertThrows(SellerCreationException.class,
                () -> sellerService.create(new SellerRequest(title, description, tin)));

        verify(sellerRepository, times(0)).save(any());
    }
    
    @Test
    void create_whenTinIsNull_throwsSellerCreationException() {
        String title = "test";
        String description = "test2";

        Assertions.assertThrows(SellerCreationException.class,
                () -> sellerService.create(new SellerRequest(title, description, null)));

        verify(sellerRepository, times(0)).save(any());
    }
}
