package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantDto;
import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.entity.Variant;
import org.aspectj.weaver.ast.Var;

import java.util.List;

public interface VariantService {
    List<Variant> getVariants();

    Variant getVariant(Long variantId);

    Variant createVariant(CreateVariantDto dto);

    Variant findVariant(Long productId, List<String> optionValue);
}
