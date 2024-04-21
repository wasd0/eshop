package com.wasd.ordermicroservice.service.brand;

import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.exception.NotFoundException;

public interface BrandService {
    BrandResponse create(String title, String description);

    BrandResponse findById(Integer id) throws NotFoundException;
}
