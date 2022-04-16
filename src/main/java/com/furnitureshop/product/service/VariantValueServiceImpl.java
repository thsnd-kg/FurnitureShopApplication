package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.OptionRepository;
import com.furnitureshop.product.repository.ProductVariantRepository;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VariantValueServiceImpl implements VariantValueService {
    private final VariantValueRepository variantValueRepository;
    private final ProductVariantRepository productVariantRepository;
    private final OptionRepository optionRepository;

    @Autowired
    public VariantValueServiceImpl(VariantValueRepository variantValueRepository,
                                   ProductVariantRepository productVariantRepository,
                                   OptionRepository optionRepository) {
        this.variantValueRepository = variantValueRepository;
        this.productVariantRepository = productVariantRepository;
        this.optionRepository = optionRepository;
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
    public VariantValue createProductVariant(VariantValueDto dto) {
        var productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        var optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (productVariantOptional.isEmpty()) {
            throw new IllegalStateException("Product variant not exists");
        } else if (optionOptional.isEmpty()) {
            throw new IllegalStateException("Option not exists");
        }

        VariantValue variantValue = new VariantValue();
        variantValue.setProductId(dto.getProductId());
        variantValue.setVariantId(dto.getVariantId());
        variantValue.setOptionId(dto.getOptionId());
        variantValue.setOptionValue(dto.getOptionValue());
        variantValue.setOptionImage(dto.getOptionImage());

        return variantValueRepository.save(variantValue);
    }

    @Override
    public VariantValue updateProductVariant(VariantValueDto dto) {
        var variantValueOptional = variantValueRepository.findById(dto.getProductId(), dto.getVariantId(), dto.getOptionId());
        var productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        var optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (variantValueOptional.isEmpty()) {
            throw new IllegalStateException("Variant value not exists");
        } else if (productVariantOptional.isEmpty()) {
            throw new IllegalStateException("Product not exists");
        } else if (optionOptional.isEmpty()) {
            throw new IllegalStateException("Option not exists");
        }

        VariantValue variantValue = variantValueOptional.get();
        variantValue.setProductId(dto.getProductId());
        variantValue.setVariantId(dto.getVariantId());
        variantValue.setOptionId(dto.getOptionId());
        variantValue.setOptionValue(dto.getOptionValue());
        variantValue.setOptionImage(dto.getOptionImage());

        return variantValueRepository.save(variantValue);
    }
}
