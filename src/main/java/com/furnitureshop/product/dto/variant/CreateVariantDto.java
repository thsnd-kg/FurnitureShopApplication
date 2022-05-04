package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVariantDto {
    @NotNull(message = "{productVariant.product.not-null}")
    private Long productId;

    @Size(max = 300, message = "{productVariant.image.size}")
    private String image;

    @Size(max = 50, message = "{productVariant.sku.size}")
    private String sku;

    private Collection<CreateValueDto> variantValues;
}
