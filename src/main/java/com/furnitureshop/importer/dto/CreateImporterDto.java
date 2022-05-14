package com.furnitureshop.importer.dto;

import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class CreateImporterDto {
    private String importDesc;

    private Integer totalPrice;

    @NotNull
    @FutureOrPresent
    private LocalDateTime createdAt;

    @NotNull
    @NotEmpty
    private List<@Valid CreateImporterDetailDto> importDetails;
}
