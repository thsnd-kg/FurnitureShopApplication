package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.GetProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.search.ProductPage;
import com.furnitureshop.product.search.ProductSearchCriteria;
import com.furnitureshop.product.service.ProductService;
import com.furnitureshop.product.service.ValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
public class ProductController {
    private final ProductService service;
    private final ValueService valueService;

    @Autowired
    public ProductController(ProductService service, ValueService valueService) {
        this.service = service;
        this.valueService = valueService;
    }

    @GetMapping("/website/products/{product-id}/option")
    public Object getOptionValue(@PathVariable("product-id") Long productId) {
        try {
            List<GetValueDto> values = valueService.getOptionValues(productId);
            values.sort(Comparator.comparing(GetValueDto::getOptionId));
            return ResponseHandler.getResponse(values, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/website/products/{product-id}")
    public Object getProductById(@PathVariable("product-id") Long productId) {
        try {
            GetProductDto product = new GetProductDto(service.getProductById(productId));
            return ResponseHandler.getResponse(product, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/website/products")
    public Object getProducts() {
        try {
            List<GetProductDto> products = service.getProducts()
                    .stream().map(GetProductDto::new)
                    .sorted(Comparator.comparing(GetProductDto::getProductId))
                    .collect(Collectors.toList());
            return ResponseHandler.getResponse(products, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/website/products/search")
    public Object getProducts(ProductPage productPage, ProductSearchCriteria productSearchCriteria) {
        try {
            Page<GetProductDto> result = service.getProducts(productPage, productSearchCriteria).map(GetProductDto::new);
            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products")
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

    @PutMapping("/products")
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

    @DeleteMapping("/products/{product-id}")
    public Object deleteProduct(@PathVariable("product-id") Long productId) {
        try {
            return ResponseHandler.getResponse(service.deleteProduct(productId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
