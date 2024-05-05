package com.wasd.productmicroservice.util.mapper;

import com.wasd.productmicroservice.data.brand.BrandResponse;
import com.wasd.productmicroservice.persistence.brand.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandResponse brandToResponse(Brand brand);
}
