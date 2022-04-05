package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.CategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository repo;

    @Override
    public List<Category> getCategories() {
        return repo.findAll();
    }

    @Override
    public List<Category> getCategoriesActive() {
        return repo.findByIsDeletedFalse();
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return repo.getById(categoryId);
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        Category category = repo.getById(categoryId);
        category.setIsDeleted(true);
        repo.save(category);
        return true;
    }

    @Override
    public Category createCategory(CategoryDto dto) {
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());
        category.setDescription(dto.getDescription());
        category.setIsDeleted(false);

        if(dto.getParentId() != null) {
            Optional<Category> parent = repo.findById(dto.getParentId());

            if (parent.isPresent())
                category.setParent(parent.get());
            else
                return null;
        }

        return repo.save(category);
    }

    @Override
    public Category updateCategory(CategoryDto dto) {
        if(dto.getCategoryId() == null)
            return null;

        Category category = repo.getById(dto.getCategoryId());

        if(dto.getCategoryName() != null)
            category.setCategoryName(dto.getCategoryName());

        if(dto.getDescription() !=null) {
            category.setDescription(dto.getDescription());
        }

        if(dto.getParentId() != null) {
            Optional<Category> parent = repo.findById(dto.getParentId());

            if (parent.isPresent())
                category.setParent(parent.get());
            else
                return null;
        }
        return repo.save(category);
    }
}
