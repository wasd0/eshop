package com.wasd.ordermicroservice.util.mapper;

import com.wasd.ordermicroservice.data.order.OrderHistoryRequest;
import com.wasd.ordermicroservice.data.order.OrderHistoryResponse;
import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.persistence.order.Order;
import com.wasd.ordermicroservice.persistence.order.OrderHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse orderToResponse(Order order);

    @Mapping(source = "price", target = "price.amount")
    Order requestToOrder(OrderRequest request);

    @Mapping(target = "orderId", source = "order.id")
    OrderHistoryResponse orderHistoryToResponse(OrderHistory orderHistory);

    @Mapping(target = "order.id", source = "orderId")
    OrderHistory requestToOrderHistory(OrderHistoryRequest request);
}
