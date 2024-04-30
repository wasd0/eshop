package com.wasd.ordermicroservice.util.mapper;

import com.wasd.ordermicroservice.data.category.CategoryResponse;
import com.wasd.ordermicroservice.persistence.category.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "parentTitle", source = "parent.title")
    CategoryResponse mapCategoryToResponse(ProductCategory category);
}
