package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.category.CreateOptionDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository repository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.repository = optionRepository;
    }

    @Override
    public List<Option> getOptions() {
        return repository.findAll();
    }

    @Override
    public Option getOptionById(Long optionId) {
        Optional<Option> option = repository.findById(optionId);

        if (!option.isPresent())
            throw new IllegalStateException("Option does not exists");

        return option.get();
    }
}
