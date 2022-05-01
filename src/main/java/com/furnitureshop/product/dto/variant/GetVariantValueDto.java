package com.furnitureshop.product.dto.variant;

import com.furnitureshop.product.entity.VariantValue;

public class GetVariantValueDto {
    private Long optionId;

    private String optionValue;

    private String optionImage;

    public GetVariantValueDto(VariantValue variantValue) {
        this.optionId = variantValue.getOption().getOptionId();
        this.optionValue = variantValue.getOptionValue();
        this.optionImage = variantValue.getOptionImage();
    }
}
