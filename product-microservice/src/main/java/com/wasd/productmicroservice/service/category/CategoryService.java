package com.wasd.productmicroservice.service.category;

import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse findById(Long id);

    CategoryResponse create(CategoryRequest request);

	List<CategoryResponse> findAll();
}
