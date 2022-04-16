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
    public Object getCategories(@RequestParam(value = "onlyActive") Boolean isActive) {
        if (isActive)
            return ResponseHandler.getResponse(service.getCategoriesActive(), HttpStatus.OK);

        return ResponseHandler.getResponse(service.getCategories(), HttpStatus.OK);
    }


    @GetMapping(path = "/{category-id}")
    public Object getCategoryById(@PathVariable("category-id") Long categoryId){
        try{
            if(categoryId == null)
                return ResponseHandler.getResponse("Id must not be null", HttpStatus.BAD_REQUEST);

            return service.getCategoryById(categoryId);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseHandler.getResponse( HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createCategory(@Valid @RequestBody CategoryDto newCategory, BindingResult errors) {
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Category category = service.createCategory(newCategory);
        if(category == null)
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(category, HttpStatus.OK);
    }

    @PutMapping
    public Object updateCategory(@RequestBody CategoryDto updatedCategory){
        Category category = service.updateCategory(updatedCategory);
        if(category == null)
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(category, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{category-id}")
    public Object deleteBrand(@PathVariable("category-id") Long categoryId){
        if(categoryId == null)
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.deleteCategory(categoryId), HttpStatus.OK);
    }



}

