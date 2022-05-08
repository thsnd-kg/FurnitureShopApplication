package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.entity.Value;

import java.util.List;

public interface ValueService {
    Value getValueById(Long variantId, Long optionId);

    List<GetValueDto> getOptionValues(Long productId);

    Variant findVariant(Long productId, List<String> optionValues);
}
