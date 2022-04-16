package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductVariantDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Variant id must not be null")
    private Long variantId;

    private Integer price;

    @NotBlank(message = "Image must not be blank")
    private String image;
}
