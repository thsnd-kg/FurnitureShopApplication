package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;

public interface VariantValueService {
    VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant);
    VariantValue getOptionValue(Long productId);
}
