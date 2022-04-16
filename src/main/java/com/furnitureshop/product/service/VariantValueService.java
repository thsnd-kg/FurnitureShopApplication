package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.entity.VariantValue;

import java.util.List;

public interface VariantValueService {
    List<VariantValue> getVariantValues();

    VariantValue getVariantValueById(Long productId, Long variantId, Long optionId);

    VariantValue createVariantValue(VariantValueDto dto);

    VariantValue updateVariantValue(VariantValueDto dto);
}
