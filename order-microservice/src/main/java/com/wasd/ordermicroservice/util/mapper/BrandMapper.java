package com.wasd.ordermicroservice.util.mapper;

import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.persistence.brand.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);

    BrandResponse brandToResponse(Brand brand);
}
