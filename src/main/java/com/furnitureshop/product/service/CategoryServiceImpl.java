package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.category.CreateCategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> getCategoriesActive() {
        return categoryRepository.findByIsDeletedFalse();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent())
            return category.get();

        throw new IllegalStateException("Category does not exist");
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        if(!isExisted(categoryId))
            throw new IllegalStateException("Category does not exist");

        Category category = categoryRepository.getById(categoryId);
        category.setIsDeleted(true);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Category createCategory(CreateCategoryDto dto) {
        return null;
    }

    @Override
    public Category updateCategory(CreateCategoryDto dto) {
        return null;
    }

    @Override
    public boolean isExisted(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.isPresent();
    }
}
