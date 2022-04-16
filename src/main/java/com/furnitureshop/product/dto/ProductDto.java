package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ProductDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Brand id must not be null")
    private Long brandId;

    @NotNull(message = "Category id must not be null")
    private Long categoryId;

    @NotBlank(message = "Product name must not be blank")
    @Size(max = 50, message = "Product name must be less than 50 characters")
    private String productName;
}
