package com.wasd.productmicroservice.util.mapper;

import com.wasd.productmicroservice.data.product.ProductCreateRequest;
import com.wasd.productmicroservice.data.product.ProductResponse;
import com.wasd.productmicroservice.persistence.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "sellerId", source = "seller.id")
    @Mapping(target = "categoryId", source = "category.id")
    @Mapping(target = "brandId", source = "brand.id")
    ProductResponse productToResponse(Product product);

    @Mapping(target = "seller.id", source = "sellerId")
    @Mapping(target = "category.id", source = "categoryId")
    @Mapping(target = "brand.id", source = "brandId")
    Product requestToProduct(ProductCreateRequest request);
}
