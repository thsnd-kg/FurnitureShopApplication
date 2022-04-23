package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.*;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository variantValueRepository;
    private final OptionService optionService;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository,
                                   OptionService optionService) {
        this.variantValueRepository = variantValueRepository;
        this.optionService = optionService;
    }

    @Override
    public List<VariantValue> getVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public VariantValue getVariantValueById(Long productId, Long variantId, Long optionId) {
        return null;
    }

    @Override
    public VariantValue createVariantValue(CreateVariantValueDto dto, ProductVariant productVariant) {
        return null;
    }

//    @Override
//    public VariantValue updateVariantValue(VariantValueDto dto) {
//        VariantValue variantValue = handleData(dto, true);
//
//        return variantValueRepository.save(variantValue);
//    }

    @Override
    public List<String> getOptionValue(Long productId, Long optionId) {
        return null;
    }
}
