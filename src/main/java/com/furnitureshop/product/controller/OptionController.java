package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.category.GetOptionDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.service.OptionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/options")
public class OptionController {
    private final OptionService service;

    public OptionController(OptionService service) {
        this.service = service;
    }

    @GetMapping
    public Object getOptions() {
        try {
            List<GetOptionDto> options = service.getOptions().stream().map(GetOptionDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(options, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{option-id}")
    public Object getOption(@PathVariable("option-id") Long optionId) {
        try {
            GetOptionDto option = new GetOptionDto(service.getOptionById(optionId));
            return ResponseHandler.getResponse(option, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
