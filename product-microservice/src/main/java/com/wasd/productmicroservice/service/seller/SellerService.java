package com.wasd.productmicroservice.service.seller;

import com.wasd.productmicroservice.data.seller.SellerRequest;
import com.wasd.productmicroservice.data.seller.SellerResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.common.NotFoundException;

public interface SellerService {
    SellerResponse findById(Long id) throws NotFoundException;

    SellerResponse create(SellerRequest request) throws AlreadyExistsException;
}
