package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.service.VariantValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/variant_value")
public class VariantValueController {
    private final VariantValueServiceImpl variantValueService;

    @Autowired
    public VariantValueController(VariantValueServiceImpl variantValueService) {
        this.variantValueService = variantValueService;
    }

    @GetMapping
    public Object getVariantValues() {
        return ResponseHandler.getResponse(variantValueService.getVariantValues(), HttpStatus.OK);
    }
}
