package com.furnitureshop.importer.dto;

import com.furnitureshop.importer.entity.Importer;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetImporterDto {
    private Long importId;
    private String importDesc;
    private Integer totalPrice;
    private List<GetImporterDetailDto> importDetails;

    public GetImporterDto(Importer importer) {
        this.importId = importer.getImportId();
        this.importDesc = importer.getImportDesc();
        this.totalPrice = importer.getTotalPrice();
        this.importDetails = importer.getImportDetails().isEmpty()
                ? new ArrayList<>()
                : importer.getImportDetails().stream().map(GetImporterDetailDto::new).collect(Collectors.toList());
    }
}
