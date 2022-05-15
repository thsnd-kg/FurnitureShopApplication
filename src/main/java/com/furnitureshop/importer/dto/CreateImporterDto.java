package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
public class CreateImporterDto {
    @NotNull(message = "Import description must not be null")
    private String importDesc;

    @NotNull(message = "Total price must not be null")
    @Positive(message = "Total price must greater than 0")
    private Integer totalPrice;

    @NotNull(message = "Import details must not be null")
    @NotEmpty(message = "Import details must not be empty")
    private List<@Valid CreateImporterDetailDto> importDetails;
}
