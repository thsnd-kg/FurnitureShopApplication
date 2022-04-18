package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.CategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Object getCategories(@RequestParam(value = "onlyActive") Boolean isActive){
        if(isActive)
            return ResponseHandler.getResponse(service.getCategoriesActive(), HttpStatus.OK);

        return ResponseHandler.getResponse(service.getCategories(), HttpStatus.OK);
    }


    @GetMapping(path = "/{category-id}")
    public Object getCategoryById(@PathVariable("category-id") Long categoryId){
        try{
            if(categoryId == null)
                throw new IllegalStateException("Category Id must not be null");

            return service.getCategoryById(categoryId);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createCategory(@Valid @RequestBody CategoryDto newCategory, BindingResult errors) {
        try {
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            Category category = service.createCategory(newCategory);
            return ResponseHandler.getResponse(category, HttpStatus.OK);
        }catch (Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public Object updateCategory(@RequestBody CategoryDto updatedCategory){
        try{
            Category category = service.updateCategory(updatedCategory);
            if(category == null)
                return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(category, HttpStatus.OK);
        }catch(Exception e){
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping(path = "/{category-id}")
    public Object deleteBrand(@PathVariable("category-id") Long categoryId) {
        try {
            if (categoryId == null)
                throw new IllegalStateException("Category Id must not be null");
            return ResponseHandler.getResponse(service.deleteCategory(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}

