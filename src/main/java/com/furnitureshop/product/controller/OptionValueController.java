package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;

import com.furnitureshop.product.service.OptionValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/option_value")
public class OptionValueController {
    private final OptionValueService optionValueService;

    @Autowired
    public OptionValueController(OptionValueService optionValueService) {
        this.optionValueService = optionValueService;
    }

    @GetMapping
    public Object getOptionValues() {
        return ResponseHandler.getResponse(optionValueService.getOptionValues(), HttpStatus.OK);
    }
}
