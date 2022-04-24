package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository variantValueRepository;
    private final OptionService optionService;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository, OptionService optionService) {
        this.variantValueRepository = variantValueRepository;
        this.optionService = optionService;
    }

    @Override
    public List<VariantValue> getVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant) {
        VariantValue variantValue = new VariantValue();

        Option option = optionService.getOptionById(dto.getOptionId());

        variantValue.setOptionValue(dto.getOptionValue());
        variantValue.setOptionImage(dto.getOptionImage());
        variantValue.setProductVariant(productVariant);
        variantValue.setOption(option);

        return variantValueRepository.save(variantValue);
    }
}
