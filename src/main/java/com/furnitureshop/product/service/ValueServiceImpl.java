package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.variant.GetValueDto;
import com.furnitureshop.product.dto.variant.UpdateValueDto;
import com.furnitureshop.product.entity.Value;
import com.furnitureshop.product.entity.ValuePK;
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

    @Autowired
    public ValueServiceImpl(ValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public Value getValueById(Long variantId, Long optionId) {
        ValuePK id = new ValuePK(variantId, optionId);
        Optional<Value> value = repository.findById(id);

        if (!value.isPresent()) {
            throw new IllegalStateException("Value is not exists");
        }

        return value.get();
    }

    @Override
    public Variant findVariant(Long productId, List<String> optionValues) {
        List<List<Variant>> result = new ArrayList<>();

        optionValues.forEach(optionValue -> {
            Optional<List<Variant>> res = repository.findVariantId(productId, optionValue);

            res.ifPresent(result::add);
        });

        for (int i = 1; i < result.size(); i++) {
            result.get(0).retainAll(result.get(i));
        }

        if (result.get(0).size() != 1)
            throw new IllegalStateException("Variant not found");

        return result.get(0).get(0);
    }

    @Override
    public List<GetValueDto> getOptionValues(Long productId) {
        return repository.getOptionValues(productId).orElse(null);
    }
}
