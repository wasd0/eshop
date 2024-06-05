package com.wasd.productmicroservice.rest;

import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;
import com.wasd.productmicroservice.service.category.CategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("{id}")
    public CategoryResponse findById(@Min(0L) @PathVariable Long id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public CategoryResponse create(@Valid @RequestBody CategoryRequest request) {
        return categoryService.create(request);
    }
	
	@GetMapping
	public List<CategoryResponse> findAll() {
		return categoryService.findAll();
	}

}
