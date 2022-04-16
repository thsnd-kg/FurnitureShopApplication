package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.CategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Category> getCategories() {
        return repository.findAll();
    }

    @Override
    public List<Category> getCategoriesActive() {
        return repository.findByIsDeletedFalse();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return repository.getById(categoryId);
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        Category category = repository.getById(categoryId);
        category.setIsDeleted(true);
        repository.save(category);
        return true;
    }

    @Override
    public Category createCategory(CategoryDto dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        category.setCategoryDescription(dto.getCategoryDescription());
        category.setIsDeleted(false);

        if(dto.getParentId() != null) {
            Optional<Category> parent = repository.findById(dto.getParentId());

            if (parent.isPresent())
                category.setParent(parent.get());
            else
                return null;
        }

        return repository.save(category);
    }

    @Override
    public Category updateCategory(CategoryDto dto) {
        if (dto.getCategoryId() == null)
            return null;

        Category category = repository.getById(dto.getCategoryId());

        if (dto.getCategoryName() != null)
            category.setCategoryName(dto.getCategoryName());

        if (dto.getCategoryDescription() != null) {
            category.setCategoryDescription(dto.getCategoryDescription());
        }

        if (dto.getParentId() != null) {
            Optional<Category> parent = repository.findById(dto.getParentId());

            if (parent.isPresent())
                category.setParent(parent.get());
            else
                return null;
        }
        return repository.save(category);
    }
}
