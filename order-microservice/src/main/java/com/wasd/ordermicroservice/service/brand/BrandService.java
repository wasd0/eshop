package com.wasd.ordermicroservice.service.brand;

import com.wasd.ordermicroservice.data.brand.BrandResponse;
import com.wasd.ordermicroservice.exception.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.NotFoundException;

public interface BrandService {
    BrandResponse create(String title, String description) throws AlreadyExistsException;

    BrandResponse findById(Integer id) throws NotFoundException;
}
