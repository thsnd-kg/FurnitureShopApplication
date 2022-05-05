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
@RequestMapping("/api/variant")
public class VariantController {
    private final VariantService service;

    @Autowired
    public VariantController(VariantService service) {
        this.service = service;
    }

    @GetMapping
    public Object getVariants() {
        return ResponseHandler.getResponse(service.getVariants(), HttpStatus.OK);
    }

    @GetMapping("/{variant-id}")
    public Object getVariant(@PathVariable("variant-id") Long variantId) {
        try {
            return ResponseHandler.getResponse(service.getVariant(variantId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createVariant(@Valid @RequestBody CreateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.createVariant(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public Object getVariant(@RequestParam Long productId, @RequestParam List<String> optionValues) {
        return ResponseHandler.getResponse(service.findVariant(productId, optionValues), HttpStatus.OK);
    }
}
