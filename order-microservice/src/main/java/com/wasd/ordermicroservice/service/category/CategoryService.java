package com.wasd.ordermicroservice.service.category;

import com.wasd.ordermicroservice.data.category.CategoryRequest;
import com.wasd.ordermicroservice.data.category.CategoryResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;

public interface CategoryService {

    CategoryResponse findById(Long id) throws NotFoundException;

    CategoryResponse create(CategoryRequest request) throws NotFoundException;
}
