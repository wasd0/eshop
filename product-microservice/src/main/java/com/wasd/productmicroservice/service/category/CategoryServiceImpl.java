package com.wasd.productmicroservice.service.category;

import com.wasd.eshopcommon.exception.NotFoundException;
import com.wasd.productmicroservice.data.category.CategoryRequest;
import com.wasd.productmicroservice.data.category.CategoryResponse;
import com.wasd.productmicroservice.exception.persistence.CategoryCreationException;
import com.wasd.productmicroservice.persistence.category.CategoryRepository;
import com.wasd.productmicroservice.persistence.category.ProductCategory;
import com.wasd.productmicroservice.util.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;
	private final CategoryMapper     mapper;

	@Override
	public CategoryResponse findById(Long id) throws NotFoundException {
		return mapper.mapCategoryToResponse(
			categoryRepository.findById(id).orElseThrow(() -> new NotFoundException(ProductCategory.class)));
	}

	@Override
	@Transactional
	public CategoryResponse create(CategoryRequest request) throws NotFoundException {
		String title = request.title();
		
		ProductCategory createCategory = mapper.requestToNewEntity(request);

		ProductCategory parent = categoryRepository.findByTitle(request.parentTitle())
			.orElseThrow(() -> new NotFoundException(ProductCategory.class));
		
		createCategory.setParent(parent);
		
		
		try {
			categoryRepository.save(createCategory);
		} catch (RuntimeException e) {
			throw new CategoryCreationException();
		}

		ProductCategory category = categoryRepository.findByTitle(title).orElseThrow(
			() -> new NotFoundException(ProductCategory.class));

		return mapper.mapCategoryToResponse(category);
	}

	@Override
	public List<CategoryResponse> findAll() {
		return categoryRepository.findAll().stream().map(mapper::mapCategoryToResponse).toList();
	}
}
