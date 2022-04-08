package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository repository;

    @Autowired
    public OptionServiceImpl(OptionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Option> getOptions() {
        return repository.findAll();
    }

    @Override
    public Option getOption(Long optionId) {
        Optional<Option> optionById = repository.getById(optionId);
        return optionById.orElse(null);
    }
}
