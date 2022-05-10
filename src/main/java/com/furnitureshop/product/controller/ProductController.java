package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.GetProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.service.ProductService;
import com.furnitureshop.product.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;
    private final ValueService valueService;

    @Autowired
    public ProductController(ProductService service, ValueService valueService) {
        this.service = service;
        this.valueService = valueService;
    }

    @GetMapping("/{product-id}/option")
    public Object getOptionValue(@PathVariable("product-id") Long productId) {
        try {
            List<GetValueDto> values = valueService.getOptionValues(productId);
            return ResponseHandler.getResponse(values, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{offset}/search")
    public Object getProducts(@RequestParam String name, @PathVariable int offset) {
        try {
            Page<GetProductDto> products = service.findByProductName(name, offset).map(GetProductDto::new);
            return ResponseHandler.getResponse(products, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{product-id}")
    public Object getProductById(@PathVariable("product-id") Long productId) {
        try {
            GetProductDto product = new GetProductDto(service.getProductById(productId));
            return ResponseHandler.getResponse(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public Object getProducts() {
        try {
            List<GetProductDto> products = service.getProducts().stream().map(GetProductDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(products, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{offset}")
    public Object getProducts(@RequestParam(value = "onlyActive") Boolean isActive, @PathVariable int offset) {
        try {
            if (isActive) {
                Page<GetProductDto> products = service.getProductsActive(offset).map(GetProductDto::new);
                return ResponseHandler.getResponse(products, HttpStatus.OK);
            }   

            Page<GetProductDto> result = service.getProducts(offset).map(GetProductDto::new);
            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createProduct(@Valid @RequestBody CreateProductDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetProductDto product = new GetProductDto(service.createProduct(dto));
            return ResponseHandler.getResponse(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateProduct(@Valid @RequestBody UpdateProductDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetProductDto product = new GetProductDto(service.updateProduct(dto));
            return ResponseHandler.getResponse(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{product-id}")
    public Object deleteProduct(@PathVariable("product-id") Long productId) {
        try {
            return ResponseHandler.getResponse(service.deleteProduct(productId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
