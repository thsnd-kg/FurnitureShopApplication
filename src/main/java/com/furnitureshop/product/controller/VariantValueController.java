package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.service.VariantValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
