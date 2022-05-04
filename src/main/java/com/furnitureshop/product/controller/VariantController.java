package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/product_variant")
public class VariantController {
    private final VariantService service;

    @Autowired
    public VariantController(VariantService service) {
        this.service = service;
    }

    @GetMapping
    public Object getProductVariants() {
        return ResponseHandler.getResponse(service.getProductVariants(), HttpStatus.OK);
    }

    @PostMapping
    public Object createProductVariant(@Valid @RequestBody CreateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createProductVariant(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/product/{product-id}")
    public Object getOptionValue(@PathVariable("product-id") Long productId) {
        return ResponseHandler.getResponse(service.getOptionValues(productId), HttpStatus.OK);
    }

    @GetMapping("/variantId")
    public Object getVariantId(@RequestParam Long productId, @RequestParam List<String> optionValues) {
        return ResponseHandler.getResponse(service.findVariantId(productId, optionValues), HttpStatus.OK);
    }
}
