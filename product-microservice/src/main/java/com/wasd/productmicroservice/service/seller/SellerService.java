package com.wasd.productmicroservice.service.seller;

import com.wasd.productmicroservice.data.seller.SellerRequest;
import com.wasd.productmicroservice.data.seller.SellerResponse;

public interface SellerService {
    SellerResponse findById(Long id);

    SellerResponse create(SellerRequest request);
}
