package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.ProductDto;
import com.furnitureshop.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Object getProducts() {
        return ResponseHandler.getResponse(productService.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}")
    public Object getBrandById(@PathVariable("product-id") Long brandId){
        return ResponseHandler.getResponse(productService.getProductById(brandId), HttpStatus.OK);
    }

    @PostMapping
    public Object createProduct(@Valid @RequestBody ProductDto newProduct, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(productService.createProduct(newProduct), HttpStatus.OK);
    }

    @PutMapping
    public Object updateBrand(@RequestBody ProductDto updatedProduct, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(productService.updateProduct(updatedProduct), HttpStatus.OK);
    }
}
