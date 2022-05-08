package com.furnitureshop.product.dto.variant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
public class CreateValueDto {
    @NotNull(message = "{option.not-null}")
    private Long optionId;

    @NotNull(message = "{option-value.not-null}")
    @Size(min = 1, max = 50, message = "{option-value.size}")
    private String optionValue;

    @NotNull(message = "{option-image.not-null}")
    @Size(min = 1, max = 300, message = "{option-image.size}")
    private String optionImage;
}
