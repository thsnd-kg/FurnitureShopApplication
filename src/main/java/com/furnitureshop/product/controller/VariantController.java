package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.dto.variant.GetVariantDto;
import com.furnitureshop.product.dto.variant.UpdateVariantDto;
import com.furnitureshop.product.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/variants")
public class VariantController {
    private final VariantService service;

    @Autowired
    public VariantController(VariantService service) {
        this.service = service;
    }

    @GetMapping
    public Object getVariants() {
        try {
            List<GetVariantDto> variants = service.getVariants().stream().map(GetVariantDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(variants, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{variant-id}")
    public Object getVariant(@PathVariable("variant-id") Long variantId) {
        try {
            GetVariantDto variant = new GetVariantDto(service.getVariantById(variantId));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createVariant(@Valid @RequestBody CreateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetVariantDto variant = new GetVariantDto(service.createVariant(dto));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateVariant(@Valid @RequestBody UpdateVariantDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetVariantDto variant = new GetVariantDto(service.updateVariant(dto));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{variant-id}")
    public Object deleteVariant(@PathVariable("variant-id") Long variantId) {
        try {
            return ResponseHandler.getResponse(service.deleteVariant(variantId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search")
    public Object getVariant(@RequestParam Long productId, @RequestParam List<String> optionValues) {
        try {
            GetVariantDto variant = new GetVariantDto(service.findVariant(productId, optionValues));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
