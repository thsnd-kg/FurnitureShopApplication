package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.OptionValue;
import com.furnitureshop.product.repository.OptionValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionValueServiceImpl implements OptionValueService {
    private final OptionValueRepository optionValueRepository;

    @Autowired
    public OptionValueServiceImpl(OptionValueRepository optionValueRepository) {
        this.optionValueRepository = optionValueRepository;
    }

    @Override
    public List<OptionValue> getOptionValues() {
        return optionValueRepository.findAll();
    }
}
