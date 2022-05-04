package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateValueDto;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.entity.Value;

import java.util.List;

public interface ValueService {
    Value createVariantValue(CreateValueDto dto, Variant variant);

    List<Object> getOptionValues(Long productId);

    List<Long> findVariantId(Long productId, List<String> optionValues);
}
