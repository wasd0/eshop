package com.wasd.productmicroservice.util.mapper;

import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationRequest;
import com.wasd.productmicroservice.data.warehouseoperation.WarehouseOperationResponse;
import com.wasd.productmicroservice.persistence.warehouseoperation.WarehouseOperation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WarehouseOperationMapper {
    WarehouseOperationMapper INSTANCE = Mappers.getMapper(WarehouseOperationMapper.class);

    WarehouseOperation requestToWarehouseOperation(WarehouseOperationRequest request);

    @Mapping(target = "productId", source = "product.id")
    WarehouseOperationResponse warehouseOperationToResponse(WarehouseOperation warehouseOperation);
}
