package com.wasd.productmicroservice.util.mapper;

import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;
import com.wasd.productmicroservice.persistence.category.ProductCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "parentTitle", source = "parent.title")
    CategoryResponse mapCategoryToResponse(ProductCategory category);

	ProductCategory requestToNewEntity(CategoryRequest request);
}
