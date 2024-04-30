package com.wasd.ordermicroservice.service.brand;

import com.wasd.ordermicroservice.data.brand.BrandRequest;
import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;

public interface BrandService {
    BrandResponse create(BrandRequest request);

    BrandResponse findById(Integer id) throws NotFoundException;
}
