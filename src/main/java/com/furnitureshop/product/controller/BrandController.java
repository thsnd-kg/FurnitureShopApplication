package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.BrandDto;
import com.furnitureshop.product.entity.Brand;
import com.furnitureshop.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    @Autowired
    private BrandService service;

    @GetMapping
    public Object getBrands(@RequestParam(value = "onlyActive") Boolean isActive){
        if(isActive == true)
            return ResponseHandler.getResponse(service.getBrandsActive(), HttpStatus.OK);

        return ResponseHandler.getResponse(service.getBrands(), HttpStatus.OK);
    }


    @GetMapping(path = "/{brand-id}")
    public Object getBrandById(@PathVariable("brand-id") Long brandId){
        return ResponseHandler.getResponse(service.getBrandById(brandId), HttpStatus.OK);
    }

    @PostMapping
    public Object createBrand(@Valid @RequestBody BrandDto newBrand, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.createBrand(newBrand), HttpStatus.OK);
    }

    @PutMapping
    public Object updateBrand(@RequestBody BrandDto updatedBrand, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.updateBrand(updatedBrand), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{brand-id}")
    public Object deleteBrand(@PathVariable("brand-id") Long brandId){
        if(brandId == null)
            return ResponseHandler.getResponse(HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.deleteBrand(brandId), HttpStatus.OK);
    }

}
