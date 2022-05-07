package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateValueDto {
    @NotNull(message = "{value.option.not-null}")
    private Long optionId;

    @NotNull(message = "{value.optionValue.not-null}")
    @Size(min = 1, max = 50, message = "{value.optionValue.size}")
    private String optionValue;

    @NotNull(message = "Option image must not be null")
    @Size(min = 1, max = 300, message = "{value.optionImage.size}")
    private String optionImage;
}
