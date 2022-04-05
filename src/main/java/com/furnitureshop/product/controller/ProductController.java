package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final ProductServiceImpl productService;

    @Autowired
    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public Object getProducts() {
        return ResponseHandler.getResponse(productService.getProducts(), HttpStatus.OK);
    }
}
