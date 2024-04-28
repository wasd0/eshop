package com.wasd.ordermicroservice.service.order;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;
import com.wasd.ordermicroservice.exception.OrderCreationException;
import com.wasd.ordermicroservice.persistence.order.Order;
import com.wasd.ordermicroservice.persistence.order.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {
    @InjectMocks
    OrderServiceImpl orderService;
    @Mock
    OrderRepository orderRepository;

    @Test
    void findAll_whenOrdersExists_returnsOrderResponseList() {
        when(orderRepository.findAll()).thenReturn(List.of(new Order(), new Order()));
        List<OrderResponse> result = orderService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
    }

    @Test
    void findAll_whenOrdersNotExists_returnsEmptyList() {
        when(orderRepository.findAll()).thenReturn(new ArrayList<>());
        List<OrderResponse> result = orderService.findAll();
        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void findById_whenCorrectId_returnsOrderResponse() throws NotFoundException {
        Long id = 1L;
        Order order = new Order();
        when(orderRepository.findById(id)).thenReturn(Optional.of(order));
        OrderResponse result = orderService.findById(id);
        Assertions.assertNotNull(result);
    }

    @Test
    void findById_whenIncorrectId_throwsNotFoundException() {
        Long orderId = 1L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> orderService.findById(orderId));
    }

    @Test
    void create_withCorrectRequestData_savesAndReturnsResponse() {
        OrderRequest request = new OrderRequest(0L, 0L, 0, BigDecimal.TEN, 0L);
        Assertions.assertDoesNotThrow(() -> orderService.create(request));
        verify(orderRepository, times(1)).save(0L, 0L, 0, BigDecimal.TEN, 0L);
    }

    @Test
    void create_withIncorrectRequestData_throwsOrderCreationException() {
        OrderRequest request = new OrderRequest(0L, 0L, 0, BigDecimal.TEN, 0L);
        willThrow(RuntimeException.class).given(orderRepository).save(0L, 0L, 0, BigDecimal.TEN, 0L);
        Assertions.assertThrows(OrderCreationException.class, () -> orderService.create(request));
    }
}
