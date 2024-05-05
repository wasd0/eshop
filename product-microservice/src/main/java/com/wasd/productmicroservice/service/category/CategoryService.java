package com.wasd.productmicroservice.service.category;

import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;
import com.wasd.productmicroservice.exception.common.NotFoundException;

public interface CategoryService {

    CategoryResponse findById(Long id) throws NotFoundException;

    CategoryResponse create(CategoryRequest request) throws NotFoundException;
}
