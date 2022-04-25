package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
public class ImporterDto {
    @NotNull(message = "Import id must not be null")
    private Long importId;

    @Size(max = 50, message = "Import description must less than 50 characters")
    private String importDesc;

    private Integer totalPrice;

    private List<ImporterDetailDto> importDetails;
}
