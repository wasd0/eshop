package com.wasd.ordermicroservice.service.category;

import com.wasd.ordermicroservice.data.category.CategoryRequest;
import com.wasd.ordermicroservice.data.category.CategoryResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.CategoryCreationException;
import com.wasd.ordermicroservice.persistence.category.CategoryRepository;
import com.wasd.ordermicroservice.persistence.category.ProductCategory;
import com.wasd.ordermicroservice.util.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse findById(Long id) throws NotFoundException {
        return CategoryMapper.INSTANCE.mapCategoryToResponse(
                categoryRepository.findById(id).orElseThrow(
                        () -> new NotFoundException(ProductCategory.class)));
    }

    @Override
    @Transactional
    public CategoryResponse create(CategoryRequest request) throws NotFoundException {
        String title = request.title();
        String parentTitle = request.parentTitle();

        try {
            categoryRepository.save(title, parentTitle);
        } catch (RuntimeException e) {
            throw new CategoryCreationException();
        }

        ProductCategory category = categoryRepository.findByTitle(title).orElseThrow(
                () -> new NotFoundException(ProductCategory.class));

        return CategoryMapper.INSTANCE.mapCategoryToResponse(category);
    }
}
