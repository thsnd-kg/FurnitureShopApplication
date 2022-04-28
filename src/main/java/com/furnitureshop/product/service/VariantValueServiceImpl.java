package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository repository;
    private final OptionService optionService;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository repository, OptionService optionService) {
        this.repository = repository;
        this.optionService = optionService;
    }

    @Override
    public VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant) {
        VariantValue variantValue = new VariantValue();

        Option option = optionService.getOptionById(dto.getOptionId());

        variantValue.setOptionValue(dto.getOptionValue());
        variantValue.setOptionImage(dto.getOptionImage());
        variantValue.setProductVariant(productVariant);
        variantValue.setOption(option);

        return repository.save(variantValue);
    }

    @Override
    public VariantValue getOptionValue(Long productId) {
        return repository.getOptionValue(productId);
    }
}
