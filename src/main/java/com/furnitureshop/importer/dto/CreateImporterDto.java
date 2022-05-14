package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
public class CreateImporterDto {
    private String importDesc;

    private Integer totalPrice;

    @NotNull
    @NotEmpty
    private List<@Valid CreateImporterDetailDto> importDetails;
}
