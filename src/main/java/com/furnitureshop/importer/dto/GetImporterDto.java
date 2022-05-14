package com.furnitureshop.importer.dto;

import com.furnitureshop.importer.entity.Importer;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetImporterDto {
    private final Long importId;
    private final String importDesc;
    private final Integer totalPrice;
    private final LocalDate createdAt;
    private final List<GetImporterDetailDto> importDetails;

    public GetImporterDto(Importer importer) {
        this.importId = importer.getImportId();
        this.importDesc = importer.getImportDesc();
        this.totalPrice = importer.getTotalPrice();
        this.createdAt = importer.getCreatedAt().toLocalDate();
        this.importDetails = importer.getImportDetails().isEmpty()
                ? new ArrayList<>()
                : importer.getImportDetails().stream().map(GetImporterDetailDto::new).collect(Collectors.toList());
    }
}
