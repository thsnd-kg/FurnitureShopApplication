package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.ProductVariantDto;
import com.furnitureshop.product.dto.variant.CreateProductVariantDto;
import com.furnitureshop.product.service.ProductVariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
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

    @PostMapping
    public Object createProductVariant(@Valid @RequestBody CreateProductVariantDto newProductVariant, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createProductVariant(newProductVariant), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateProductVariant(@Valid @RequestBody ProductVariantDto updatedProductVariant, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.updateProductVariant(updatedProductVariant), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
