package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class ImporterDetailDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Variant id must not be null")
    private Long variantId;

    @NotNull(message = "Quantity must not be null")
    @Positive(message = "Quantity must be positive number")
    private Integer quantity;

    @NotNull(message = "Product id must not be null")
    @Positive(message = "Price must be positive number")
    private Integer price;
}
