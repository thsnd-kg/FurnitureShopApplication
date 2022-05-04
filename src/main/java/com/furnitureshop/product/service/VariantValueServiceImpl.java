package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateVariantValueDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.entity.ProductVariant;
import com.furnitureshop.product.entity.VariantValue;
import com.furnitureshop.product.repository.VariantValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Long> findVariantId(Long productId, List<String> optionValues) {
        List<List<Long>> result = new ArrayList<>();

        optionValues.forEach(optionValue -> {
            Optional<List<Long>> res = repository.findVariantId(productId, optionValue);

            res.ifPresent(result::add);
        });

        for (int i = 1; i < result.size(); i++) {
            result.get(0).retainAll(result.get(i));
        }

        return result.get(0);
    }

    @Override
    public List<Object> getOptionValues(Long productId) {
        return repository.getOptionValues(productId).orElse(null);
    }
}
