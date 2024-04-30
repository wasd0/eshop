package com.wasd.ordermicroservice.rest;

import com.wasd.ordermicroservice.data.category.CategoryRequest;
import com.wasd.ordermicroservice.data.category.CategoryResponse;
import com.wasd.ordermicroservice.exception.common.AlreadyExistsException;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.service.category.CategoryService;
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
