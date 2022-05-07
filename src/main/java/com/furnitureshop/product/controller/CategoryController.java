package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.category.CreateCategoryDto;
import com.furnitureshop.product.dto.category.GetCategoryDto;
import com.furnitureshop.product.dto.category.UpdateCategoryDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public Object getCategories(@RequestParam(value = "onlyActive") Boolean isActive) {
        if (isActive) {
            List<GetCategoryDto> categories = service.getCategoriesActive().stream().map(GetCategoryDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(categories, HttpStatus.OK);
        }

        List<GetCategoryDto> categories = service.getCategories().stream().map(GetCategoryDto::new).collect(Collectors.toList());
        return ResponseHandler.getResponse(categories, HttpStatus.OK);
    }


    @GetMapping(path = "/{category-id}")
    public Object getCategoryById(@PathVariable("category-id") Long categoryId) {
        try {
            GetCategoryDto category = new GetCategoryDto(service.getCategoryById(categoryId));
            return ResponseHandler.getResponse(category, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createCategory(@Valid @RequestBody CreateCategoryDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetCategoryDto category = new GetCategoryDto(service.createCategory(dto));
            return ResponseHandler.getResponse(category, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping
    public Object updateCategory(@Valid @RequestBody UpdateCategoryDto updatedCategory, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetCategoryDto category = new GetCategoryDto(service.updateCategory(updatedCategory));
            return ResponseHandler.getResponse(category, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(path = "/{category-id}")
    public Object deleteBrand(@PathVariable("category-id") Long categoryId) {
        try {
            return ResponseHandler.getResponse(service.deleteCategory(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}

