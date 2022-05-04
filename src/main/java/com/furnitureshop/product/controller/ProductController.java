package com.furnitureshop.product.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.product.dto.product.CreateProductDto;
import com.furnitureshop.product.dto.product.GetProductDto;
import com.furnitureshop.product.dto.product.UpdateProductDto;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.service.ProductService;
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
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Object getProducts(@PathVariable int offset, @PathVariable int pageSize) {
        try {
            Page<GetProductDto> result = service.getProductsWithPagination(offset, pageSize).map(GetProductDto::new);
            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/page/{offset}")
    public Object getProducts(@RequestParam String name, @PathVariable int offset) {
        try {
            Page<GetProductDto> result = service.findByProductName(name, offset).map(GetProductDto::new);
            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{product-id}")
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
            List<GetProductDto> result = service.getProducts().stream().map(GetProductDto::new).collect(Collectors.toList());
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

            return ResponseHandler.getResponse(service.createProduct(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public Object updateProduct(@Valid @RequestBody UpdateProductDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(service.updateProduct(dto), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
