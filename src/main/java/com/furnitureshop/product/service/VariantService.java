package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.entity.Variant;

import java.util.List;

public interface VariantService {
    List<Variant> getProductVariants();

    Variant createProductVariant(CreateVariantDto dto);

    List<Object> getOptionValues(Long productId);

    List<Long> findVariantId(Long productId, List<String> optionValue);
}
