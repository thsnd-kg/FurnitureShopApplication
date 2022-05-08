package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class CreateImporterDetailDto {
    @NotNull
    private Long variantId;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @Positive
    private Integer price;
}
