package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;

import java.util.List;

public interface VariantValueService {
    List<VariantValue> getVariantValues();

    VariantValue getVariantValueById(Long productId, Long variantId, Long optionId);

    VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant);

    //VariantValue updateVariantValue(VariantValueDto dto);

    List<String> getOptionValue(Long productId, Long optionId);
}
