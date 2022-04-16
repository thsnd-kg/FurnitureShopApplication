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
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public Object getProducts() {
        return ResponseHandler.getResponse(service.getProducts(), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}")
    public Object getProductById(@PathVariable("product-id") Long productId) {
        return ResponseHandler.getResponse(service.getProduct(productId), HttpStatus.OK);
    }

    @PostMapping
    public Object createProduct(@Valid @RequestBody ProductDto newProduct, BindingResult errors){
        if(errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.createProduct(newProduct), HttpStatus.OK);
    }

    @PutMapping
    public Object updateProduct(@Valid @RequestBody ProductDto updatedProduct, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.updateProduct(updatedProduct), HttpStatus.OK);
    }
}
