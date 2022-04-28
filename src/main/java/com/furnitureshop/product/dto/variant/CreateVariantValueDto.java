package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateVariantValueDto {
    @NotNull(message = "{variantValue.option.not-null}")
    private Long optionId;

    @NotNull(message = "{variantValue.optionValue.not-null}")
    @Size(max = 50, message = "{variantValue.optionValue.size}")
    private String optionValue;

    @Size(max = 300, message = "{variantValue.optionImage.size}")
    private String optionImage;
}
