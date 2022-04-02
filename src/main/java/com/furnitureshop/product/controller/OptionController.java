package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.service.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/option")
public class OptionController {
    private final OptionServiceImpl optionService;

    @Autowired
    public OptionController(OptionServiceImpl optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public Object getOptions() {
        return ResponseHandler.getResponse(optionService.getOptions(), HttpStatus.OK);
    }
}
