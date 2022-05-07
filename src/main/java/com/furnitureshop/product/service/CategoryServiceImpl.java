package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.category.CreateCategoryDto;
import com.furnitureshop.product.dto.category.CreateOptionDto;
import com.furnitureshop.product.dto.category.UpdateCategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final OptionService optionService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository, OptionService optionService) {
        this.repository = repository;
        this.optionService = optionService;
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
        Optional<Category> category = repository.findById(categoryId);

        if (!category.isPresent())
            throw new IllegalStateException("Category does not exists");

        return category.get();
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        if(!isExisted(categoryId))
            throw new IllegalStateException("Category does not exist");

        Category category = repository.getOne(categoryId);
        category.setIsDeleted(true);
        repository.save(category);

        return true;
    }

    @Override
    public Category createCategory(CreateCategoryDto dto) {
        Category category = new Category();

        category.setCategoryDesc(dto.getCategoryDesc());
        category.setCategoryName(dto.getCategoryName());

        if (dto.getParentId() != null) {
            Optional<Category> parent = repository.findById(dto.getParentId());

            if (!parent.isPresent()) {
                throw new IllegalStateException("Parent does not exists");
            }

            category.setParent(parent.get());
        }

        Category result = repository.save(category);

        result.setOptions(optionService.createOption(dto.getOptions(), result));

        return result;
    }

    @Override
    public Category updateCategory(UpdateCategoryDto dto) {
        Category category = getCategoryById(dto.getCategoryId());

        category.setCategoryName(dto.getCategoryName());
        category.setCategoryDesc(dto.getCategoryDesc());

        if (dto.getParentId() != null) {
            Optional<Category> parent = repository.findById(dto.getParentId());

            if (!parent.isPresent()) {
                throw new IllegalStateException("Parent does not exists");
            }

            category.setParent(parent.get());
        }

        return repository.save(category);
    }

    @Override
    public boolean isExisted(Long categoryId) {
        Optional<Category> category = repository.findById(categoryId);
        return category.isPresent();
    }
}
