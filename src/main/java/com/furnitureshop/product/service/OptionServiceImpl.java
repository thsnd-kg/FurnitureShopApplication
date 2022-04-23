package com.furnitureshop.product.service;

import com.furnitureshop.product.dto.category.CreateOptionDto;
import com.furnitureshop.product.entity.Category;
import com.furnitureshop.product.entity.Option;
import com.furnitureshop.product.repository.OptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;

    @Autowired
    public OptionServiceImpl(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    @Override
    public List<Option> getOptions() {
        return optionRepository.findAll();
    }

    @Override
    public Option getOptionByOptionId(Long optionId) {
        return optionRepository.findById(optionId).orElse(null);
    }

    @Override
    public Option createOption(CreateOptionDto dto, Category category) {
        Option option = new Option();

        option.setCategory(category);
        option.setOptionName(dto.getOptionName());

        return optionRepository.save(option);
    }

//    @Override
//    public Option updateOption(CreateOptionDto dto, Category category) {
//        Option option = handleData(dto, true, category);
//        return optionRepository.save(option);
//    }
}
