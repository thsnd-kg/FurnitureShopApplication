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
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class VariantController {
    private final VariantService service;

    @Autowired
    public VariantController(VariantService service) {
        this.service = service;
    }

    @GetMapping("/website/variants")
    public Object getVariants(@RequestParam(name = "onlyActive") Boolean isActive, @RequestParam Long productId) {
        try {
            if (isActive) {
                List<GetVariantDto> variants = service.getVariantsActiveByProductId(productId)
                        .stream().map(GetVariantDto::new)
                        .sorted(Comparator.comparing(GetVariantDto::getVariantId))
                        .collect(Collectors.toList());
                return ResponseHandler.getResponse(variants, HttpStatus.OK);
            }

            List<GetVariantDto> variants = service.getVariantsByProductId(productId)
                    .stream().map(GetVariantDto::new)
                    .sorted(Comparator.comparing(GetVariantDto::getVariantId))
                    .collect(Collectors.toList());
            return ResponseHandler.getResponse(variants, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/website/variants/{variant-id}")
    public Object getVariant(@PathVariable("variant-id") Long variantId) {
        try {
            GetVariantDto variant = new GetVariantDto(service.getVariantById(variantId));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/variants")
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

    @PutMapping("/variants")
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

    @DeleteMapping("/variants/{variant-id}")
    public Object deleteVariant(@PathVariable("variant-id") Long variantId) {
        try {
            return ResponseHandler.getResponse(service.deleteVariant(variantId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/website/variants/search")
    public Object getVariant(@RequestParam Long productId, @RequestParam List<String> optionValues) {
        try {
            GetVariantDto variant = new GetVariantDto(service.findVariant(productId, optionValues));
            return ResponseHandler.getResponse(variant, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
