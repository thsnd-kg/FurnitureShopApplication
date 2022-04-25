package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.category.CreateCategoryDto;
import com.furnitureshop.product.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getCategories();

    List<Category> getCategoriesActive();

    Category getCategoryById(Long categoryId);

    Boolean deleteCategory(Long categoryId);

    Category createCategory(CreateCategoryDto dto);

    Category updateCategory(CreateCategoryDto updatedCategory);

    boolean isExisted(Long categoryId);
}
