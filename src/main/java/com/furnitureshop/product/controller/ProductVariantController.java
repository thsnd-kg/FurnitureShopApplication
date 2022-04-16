package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/product_variant")
public class ProductVariantController {
    private final ProductVariantService service;

    @Autowired
    public ProductVariantController(ProductVariantService service) {
        this.service = service;
    }

    @GetMapping
    public Object getProductVariants() {
        return ResponseHandler.getResponse(service.getProductVariants(), HttpStatus.OK);
    }

    @GetMapping(path = "/{variant-id}")
    public Object getProductVariantByVariantId(@PathVariable("variant-id") Long variantId) {
        return ResponseHandler.getResponse(service.getProductVariantByVariantId(variantId), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}")
    public Object getProductVariantByProductId(@PathVariable("product-id") Long productId) {
        return ResponseHandler.getResponse(service.getProductVariantByProductId(productId), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}/{variant-id}")
    public Object getOptionById(@PathVariable("product-id") Long productId, @PathVariable("variant-id") Long variantId) {
        return ResponseHandler.getResponse(service.getProductVariantById(variantId, productId), HttpStatus.OK);
    }

    @PostMapping
    public Object createProductVariant(@Valid @RequestBody ProductVariantDto newProductVariant, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.createProductVariant(newProductVariant), HttpStatus.OK);
    }

    @PutMapping
    public Object updateProductVariant(@Valid @RequestBody ProductVariantDto updatedProductVariant, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.updateProductVariant(updatedProductVariant), HttpStatus.OK);
    }
}
