package com.furnitureshop.importer.dto;

import com.furnitureshop.importer.entity.ImporterDetail;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class GetImporterDetailDto {
    private final Long variantId;
    private final Integer quantity;
    private final Integer price;

    public GetImporterDetailDto(ImporterDetail importerDetail) {
        this.variantId = importerDetail.getVariant().getVariantId();
        this.quantity = importerDetail.getQuantity();
        this.price = importerDetail.getPrice();
    }
}
