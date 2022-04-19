package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.entity.*;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository variantValueRepository;
    private final ProductVariantService productVariantService;
    private final OptionService optionService;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository,
                                   ProductVariantService productVariantService,
                                   OptionService optionService) {
        this.variantValueRepository = variantValueRepository;
        this.productVariantService = productVariantService;
        this.optionService = optionService;
    }

    @Override
    public List<VariantValue> getVariantValues() {
        return variantValueRepository.findAll();
    }

    @Override
    public VariantValue getVariantValueById(Long productId, Long variantId, Long optionId) {
        return variantValueRepository.findById(productId, variantId, optionId).orElse(null);
    }

    @Override
    public VariantValue createVariantValue(VariantValueDto dto) {
        VariantValue variantValue = handleData(dto, false);

        return variantValueRepository.save(variantValue);
    }

    @Override
    public VariantValue updateVariantValue(VariantValueDto dto) {
        VariantValue variantValue = handleData(dto, true);

        return variantValueRepository.save(variantValue);
    }

    @Override
    public List<String> getOptionValue(Long productId, Long optionId) {
        return variantValueRepository.getOptionValue(productId, optionId);
    }

    public boolean isExisted(Long productId, Long variantId, Long optionId) {
        Optional<VariantValue> variantValue = variantValueRepository.findById(new VariantValuePK(productId, variantId, optionId));

        return variantValue.isPresent();
    }

    public VariantValue handleData(VariantValueDto dto, boolean hasId) {
        VariantValue variantValue = new VariantValue();

        if (hasId) {
            if (dto.getProductId() == null)
                throw new IllegalStateException("Product id must not be null");
            if (dto.getVariantId() == null)
                throw new IllegalStateException("Variant id must not be null");
            if (dto.getOptionId() == null)
                throw new IllegalStateException("Option id must not be null");

            if (isExisted(dto.getProductId(), dto.getVariantId(), dto.getOptionId()))
                variantValue = variantValueRepository.getById(new VariantValuePK(dto.getProductId(), dto.getVariantId(), dto.getOptionId()));
            else
                throw new IllegalStateException("Product variant not exists");
        }

        ProductVariant productVariant = productVariantService.getProductVariantById(dto.getVariantId(), dto.getProductId());
        Option option = optionService.getOptionById(dto.getOptionId(), dto.getCategoryId());

        variantValue.setProductId(dto.getProductId());
        variantValue.setVariantId(dto.getVariantId());
        variantValue.setOptionId(dto.getOptionId());
        variantValue.setOptionValue(dto.getOptionValue());
        variantValue.setProductVariant(productVariant);
        variantValue.setOption(option);

        return variantValue;
    }
}
