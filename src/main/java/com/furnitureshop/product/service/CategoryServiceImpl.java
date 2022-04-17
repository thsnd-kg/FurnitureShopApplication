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
    private final CategoryRepository repo;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repo = repository;
    }

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
        Optional<Category> category = repo.findById(categoryId);
        if(category.isPresent())
            return category.get();

        throw new IllegalStateException("Category does not exist");
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        if(!isExisted(categoryId))
            throw new IllegalStateException("Category does not exist");

        Category category = repo.getById(categoryId);
        category.setIsDeleted(true);
        repo.save(category);
        return true;
    }

    @Override
    public Category createCategory(CategoryDto dto) {
        Category category = handleData(dto, false);
        return repo.save(category);
    }

    @Override
    public Category updateCategory(CategoryDto dto) {
        Category category = handleData(dto, true);
        return repo.save(category);
    }

    @Override
    public boolean isExisted(Long categoryId){
        Optional<Category> category = repo.findById(categoryId);
        if(category.isPresent())
            return true;

        return false;
    }

    public Category handleData(CategoryDto dto, boolean hasId){
        Category category = new Category();

        if(hasId) {
            if (dto.getCategoryId() == null)
                throw new IllegalStateException("Category Id must not be null");

            if (isExisted(dto.getCategoryId()))
                category = repo.getById(dto.getCategoryId());
        }

        if(dto.getCategoryName() != null)
            category.setCategoryName(dto.getCategoryName());

        if(dto.getCategoryDescription() !=null) {
            category.setCategoryDescription(dto.getCategoryDescription());
        }

        if(dto.getParentId() != null) {
            if(!isExisted(dto.getParentId()))
                throw new IllegalStateException("Parent does not exist");

            Category parent = repo.getById(dto.getParentId());
            category.setParent(parent);
        }
        return  category;
    }
}
