package com.furnitureshop.product.dto.variant;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Getter
@Setter
public class CreateProductVariantDto {
    @NotNull(message = "Product id must not be null")
    private Long productId;

    @NotBlank(message = "Image must not be blank")
    private String image;

    @NotBlank(message = "Sku must not be blank")
    private String sku;

    private Collection<CreateVariantValueDto> variantValues;
}
