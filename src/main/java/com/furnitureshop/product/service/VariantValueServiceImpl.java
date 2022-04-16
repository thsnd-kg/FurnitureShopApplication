package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.VariantValueDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.OptionRepository;
import com.furnitureshop.product.repository.ProductVariantRepository;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public VariantValue createVariantValue(VariantValueDto dto) {
        Optional<ProductVariant> productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        Optional<Option> optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (!productVariantOptional.isPresent()) {
            throw new IllegalStateException("Product variant not exists");
        } else if (!optionOptional.isPresent()) {
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
    public VariantValue updateVariantValue(VariantValueDto dto) {
        Optional<VariantValue> variantValueOptional = variantValueRepository.findById(dto.getProductId(), dto.getVariantId(), dto.getOptionId());
        Optional<ProductVariant> productVariantOptional = productVariantRepository.findById(dto.getVariantId(), dto.getProductId());
        Optional<Option> optionOptional = optionRepository.findById(dto.getOptionId(), dto.getCategoryId());

        if (!variantValueOptional.isPresent()) {
            throw new IllegalStateException("Variant value not exists");
        } else if (!productVariantOptional.isPresent()) {
            throw new IllegalStateException("Product not exists");
        } else if (!optionOptional.isPresent()) {
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
