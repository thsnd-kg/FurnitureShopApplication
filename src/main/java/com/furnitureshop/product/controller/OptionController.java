package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.OptionDto;
import com.furnitureshop.product.service.OptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/option")
public class OptionController {
    private final OptionService service;

    @Autowired
    public OptionController(OptionService service) {
        this.service = service;
    }

    @GetMapping
    public Object getOptions() {
        return ResponseHandler.getResponse(service.getOptions(), HttpStatus.OK);
    }

    @GetMapping(path = "/{option-id}")
    public Object getOptionByOptionId(@PathVariable("option-id") Long optionId) {
        return ResponseHandler.getResponse(service.getOptionsByOptionId(optionId), HttpStatus.OK);
    }

    @GetMapping(path = "/{category-id}")
    public Object getOptionByCategoryId(@PathVariable("category-id") Long categoryId) {
        return ResponseHandler.getResponse(service.getOptionsByCategoryId(categoryId), HttpStatus.OK);
    }

    @GetMapping(path = "/{option-id}/{category-id}")
    public Object getOptionById(@PathVariable("option-id") Long optionId, @PathVariable("category-id") Long categoryId) {
        return ResponseHandler.getResponse(service.getOptionById(optionId, categoryId), HttpStatus.OK);
    }

    @PostMapping
    public Object createOption(@Valid @RequestBody OptionDto newOption, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.createOption(newOption), HttpStatus.OK);
    }

    @PutMapping
    public Object updateOption(@Valid @RequestBody OptionDto updatedOption, BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        return ResponseHandler.getResponse(service.updateOption(updatedOption), HttpStatus.OK);
    }
}
