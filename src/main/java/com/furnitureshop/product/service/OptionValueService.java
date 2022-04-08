package com.furnitureshop.product.service;

import com.furnitureshop.product.entity.OptionValue;

import java.util.List;

public interface OptionValueService {
    List<OptionValue> getOptionValues();
    OptionValue getOptionValueByOptionId(Long optionId);
    OptionValue getOptionValueByValueId(Long valueId);
    OptionValue getOptionValueByProductId(Long productId);
}
