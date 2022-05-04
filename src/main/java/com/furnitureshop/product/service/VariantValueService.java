package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;

import java.util.List;

public interface VariantValueService {
    VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant);

    List<Object> getOptionValues(Long productId);

    List<Long> findVariantId(Long productId, List<String> optionValues);
}
