package com.wasd.ordermicroservice.service.seller;

import com.wasd.ordermicroservice.data.seller.SellerRequest;
import com.wasd.ordermicroservice.data.seller.SellerResponse;
import com.wasd.ordermicroservice.exception.common.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.common.NotFoundException;

public interface SellerService {
    SellerResponse findById(Long id) throws NotFoundException;

    SellerResponse create(SellerRequest request) throws AlreadyExistsException;
}
