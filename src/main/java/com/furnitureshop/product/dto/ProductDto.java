package com.furnitureshop.product.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class ProductDto {
    private Long productId;
    private Long brandId;
    private Long categoryId;

    @NotNull(message = "Product name must not be null")
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 1, max = 50, message = "Product name must be between 1 and 50 characters")
    private String productName;
}
