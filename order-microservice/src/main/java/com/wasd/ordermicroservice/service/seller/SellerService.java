package com.wasd.ordermicroservice.service.seller;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;

public interface SellerService {
    SellerResponse findById(Long id);

    SellerResponse create(SellerRequest request);
}
