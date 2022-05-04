package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.CreateValueDto;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.entity.Value;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.repository.ValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ValueServiceImpl implements ValueService {
    private final ValueRepository repository;
    private final OptionService optionService;

    @Autowired
    public ValueServiceImpl(ValueRepository repository, OptionService optionService) {
        this.repository = repository;
        this.optionService = optionService;
    }

    @Override
    public Value createVariantValue(CreateValueDto dto, Variant variant) {
        Value value = new Value();

        Option option = optionService.getOptionById(dto.getOptionId());

        value.setOptionValue(dto.getOptionValue());
        value.setOptionImage(dto.getOptionImage());
        value.setVariant(variant);
        value.setOption(option);

        return repository.save(value);
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
