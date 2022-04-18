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
    public Category createCategory(CategoryDto dto) {
        Category category = handleData(dto, false);
        Category result = categoryRepository.save(category);
        dto.getOptionDtos().forEach(optionDto -> {
            optionService.createOption(optionDto, category);
        });

        return result;
    }

    @Override
    public Category updateCategory(CategoryDto dto) {
        Category category = handleData(dto, true);
        return categoryRepository.save(category);
    }

    @Override
    public boolean isExisted(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        return category.isPresent();
    }

    public Category handleData(CategoryDto dto, boolean hasId){
        Category category = new Category();

        if(hasId) {
            if (dto.getCategoryId() == null)
                throw new IllegalStateException("Category Id must not be null");

            if (isExisted(dto.getCategoryId()))
                category = categoryRepository.getById(dto.getCategoryId());
        }

        if(dto.getCategoryName() != null)
            category.setCategoryName(dto.getCategoryName());

        if (dto.getCategoryDescription() != null) {
            category.setCategoryDescription(dto.getCategoryDescription());
        }

        if(dto.getParentId() != null) {
            if(!isExisted(dto.getParentId()))
                throw new IllegalStateException("Parent does not exist");

            Category parent = categoryRepository.getById(dto.getParentId());
            category.setParent(parent);
        }
        return  category;
    }
}
