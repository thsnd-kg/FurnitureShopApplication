package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.OptionValue;
import com.furnitureshop.product.repository.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionValueServiceImpl implements OptionValueService {
    private final OptionValueRepository repository;

    @Autowired
    public OptionValueServiceImpl(OptionValueRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OptionValue> getOptionValues() {
        return repository.findAll();
    }

    @Override
    public OptionValue getOptionValueByOptionId(Long optionId) {
        return repository.getOptionValueByOptionId(optionId).orElse(null);
    }

    @Override
    public OptionValue getOptionValueByValueId(Long valueId) {
        return repository.getOptionValueByValueId(valueId).orElse(null);
    }

    @Override
    public OptionValue getOptionValueByProductId(Long productId) {
        return repository.getOptionValueByProductId(productId).orElse(null);
    }
}
