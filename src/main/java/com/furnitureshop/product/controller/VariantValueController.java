package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.service.VariantValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/variant_value")
public class VariantValueController {
    private final VariantValueService service;

    @Autowired
    public VariantValueController(VariantValueService service) {
        this.service = service;
    }

    @GetMapping
    public Object getVariantValues() {
        return ResponseHandler.getResponse(service.getVariantValues(), HttpStatus.OK);
    }

    @GetMapping(path = "/{product-id}/{variant-id}/{option-id}")
    public Object getVariantValueById(@PathVariable("product-id") Long productId,
                                      @PathVariable("variant-id") Long variantId,
                                      @PathVariable("option-id") Long optionId) {
        return ResponseHandler.getResponse(service.getVariantValueById(productId, variantId, optionId), HttpStatus.OK);
    }

    @PostMapping
    public Object createProductVariant(@Valid @RequestBody VariantValueDto newVariantValue, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createVariantValue(newVariantValue), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateProductVariant(@Valid @RequestBody VariantValueDto updatedVariantValue, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.updateVariantValue(updatedVariantValue), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
