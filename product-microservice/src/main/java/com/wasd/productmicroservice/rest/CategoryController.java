package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;
import com.wasd.productmicroservice.exception.common.AlreadyExistsException;
import com.wasd.productmicroservice.exception.common.NotFoundException;
import com.wasd.productmicroservice.service.category.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("{id}")
    public CategoryResponse findById(@Min(0L) @PathVariable Long id) throws NotFoundException {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryResponse create(@Valid @RequestBody CategoryRequest request) throws AlreadyExistsException, NotFoundException {
        return categoryService.create(request);
    }

}
