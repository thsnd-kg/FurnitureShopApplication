package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class CreateImporterDto {
    @NotNull
    private Long userId;

    @NotNull
    private String importDesc;

    @NotNull
    @Positive
    private Integer totalPrice;

    @NotNull
    @NotEmpty
    private List<@Valid CreateImporterDetailDto> importDetails;
}
