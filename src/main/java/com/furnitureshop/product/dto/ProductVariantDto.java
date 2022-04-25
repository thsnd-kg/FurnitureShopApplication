package com.furnitureshop.product.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class ProductVariantDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotNull(message = "Variant id must not be null")
    private Long variantId;

    @NotBlank(message = "Price must not be blank")
    private Integer price;

    @NotBlank(message = "Image must not be blank")
    private String image;

    @NotBlank(message = "Sku must not be blank")
    private String sku;

    private Collection<VariantValueDto> variantValues;
}
