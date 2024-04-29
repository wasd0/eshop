package com.wasd.ordermicroservice.util.mapper;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import com.wasd.ordermicroservice.persistence.seller.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SellerMapper {
    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    SellerResponse sellerToResponse(Seller seller);
    Seller requestToSeller(SellerRequest request);
}
