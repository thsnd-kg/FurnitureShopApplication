package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.CategoryDto;
import com.furnitureshop.product.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();
    List<Category> getCategoriesActive();
    Category getCategoryById(Long categoryId);
    Boolean deleteCategory(Long categoryId);
    Category createCategory(CategoryDto dto);
    Category updateCategory(CategoryDto updatedCategory);
}
