package com.wasd.productmicroservice.service.brand;

import com.wasd.productmicroservice.data.brand.BrandRequest;
import com.wasd.productmicroservice.data.brand.BrandResponse;

public interface BrandService {
    BrandResponse create(BrandRequest request);

    BrandResponse findById(Integer id);
}
