package com.wasd.ordermicroservice.service.category;

import com.wasd.ordermicroservice.data.category.CategoryRequest;
import com.wasd.ordermicroservice.data.category.CategoryResponse;
import com.wasd.ordermicroservice.exception.common.NotFoundException;
import com.wasd.ordermicroservice.exception.persistence.CategoryCreationException;
import com.wasd.ordermicroservice.persistence.category.CategoryRepository;
import com.wasd.ordermicroservice.persistence.category.ProductCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {
    @InjectMocks
    CategoryServiceImpl categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @Test
    void findById_whenIncorrectId_ThrowsNotFoundException() {
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(NotFoundException.class, () -> categoryService.findById(id));
    }

    @Test
    void findById_whenCorrectId_returnsResponse() throws NotFoundException {
        Long id = 1L;
        when(categoryRepository.findById(id)).thenReturn(Optional.of(new ProductCategory(id, "", null, null, null)));
        CategoryResponse result = categoryService.findById(id);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(id, result.id());
    }

    @Test
    void create_withNullParent_savesAndReturnsResponse() {
        String title = "test";
        ProductCategory category = new ProductCategory();
        category.setTitle(title);
        when(categoryRepository.findByTitle(title)).thenReturn(Optional.of(category));
        Assertions.assertDoesNotThrow(() -> categoryService.create(new CategoryRequest(title, null)));
        verify(categoryRepository, times(1)).save(title, null);
    }

    @Test
    void create_withCorrectParent_savesAndReturnsResponse() {
        String title = "test";
        String parentName = "parent";
        ProductCategory category = new ProductCategory();
        category.setTitle(title);
        when(categoryRepository.findByTitle(title)).thenReturn(Optional.of(category));
        Assertions.assertDoesNotThrow(() -> categoryService.create(new CategoryRequest(title, parentName)));
        verify(categoryRepository, times(1)).save(title, parentName);
    }

    @Test
    void create_withIncorrectParent_throwsCategoryCreationException() {
        String title = "test";
        String parentName = "parent";
        willThrow(RuntimeException.class).given(categoryRepository).save(title, parentName);
        Assertions.assertThrows(CategoryCreationException.class, () -> categoryService.create(new CategoryRequest(title, parentName)));
    }

    @Test
    void create_withIncorrectTitle_throwsCategoryCreationException() {
        String title = "test";
        when(categoryRepository.findByTitle(title)).thenThrow(CategoryCreationException.class);
        Assertions.assertThrows(CategoryCreationException.class, () -> categoryService.create(new CategoryRequest(title, null)));
    }
}