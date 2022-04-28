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
    private final OptionService optionService;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, OptionService optionService) {
        this.categoryRepository = categoryRepository;
        this.optionService = optionService;
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

        if (!category.isPresent())
            throw new IllegalStateException("Category does not exists");

        return category.get();
    }

    @Override
    public Boolean deleteCategory(Long categoryId) {
        if(!isExisted(categoryId))
            throw new IllegalStateException("Category does not exist");

        Category category = categoryRepository.getOne(categoryId);
        category.setIsDeleted(true);
        categoryRepository.save(category);
        return true;
    }

    @Override
    public Category createCategory(CreateCategoryDto dto) {
        Category category = new Category();

        category.setCategoryDesc(dto.getCategoryDesc());
        category.setCategoryName(dto.getCategoryName());

        if (dto.getParentId() != null) {
            Category parent = categoryRepository.findById(dto.getParentId()).orElse(null);

            if (parent == null) {
                throw new IllegalStateException("Parent does not exists");
            }

            category.setParent(parent);
        }

        Category result = categoryRepository.save(category);

        dto.getOptionDtos().forEach(option -> optionService.createOption(option, result));

        return result;
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
