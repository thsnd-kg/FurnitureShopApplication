package com.furnitureshop.importer.dto;

import com.furnitureshop.importer.entity.ImporterDetail;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class GetImporterDetailDto {
    private Long variantId;
    private Integer quantity;
    private Integer price;

    public GetImporterDetailDto(ImporterDetail importerDetail) {
        this.variantId = importerDetail.getVariant().getVariantId();
        this.quantity = importerDetail.getQuantity();
        this.price = importerDetail.getPrice();
    }
}
