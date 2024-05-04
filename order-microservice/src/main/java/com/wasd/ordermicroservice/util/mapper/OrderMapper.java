package com.wasd.ordermicroservice.util.mapper;

import com.wasd.ordermicroservice.data.order.OrderRequest;
import com.wasd.ordermicroservice.data.order.OrderResponse;
import com.wasd.ordermicroservice.persistence.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    
    OrderResponse orderToResponse(Order order);

    @Mapping(source = "price", target = "price.amount")
    Order requestToOrder(OrderRequest request);
}
