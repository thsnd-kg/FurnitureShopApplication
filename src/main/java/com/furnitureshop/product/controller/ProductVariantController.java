package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.service.ProductVariantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product_variant")
public class ProductVariantController {
    private final ProductVariantServiceImpl productVariantService;

    @Autowired
    public ProductVariantController(ProductVariantServiceImpl productVariantService) {
        this.productVariantService = productVariantService;
    }

    @GetMapping
    public Object getProductVariants() {
        return ResponseHandler.getResponse(productVariantService.getProductVariants(), HttpStatus.OK);
    }
}
