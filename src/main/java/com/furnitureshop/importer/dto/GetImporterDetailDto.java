package com.furnitureshop.importer.dto;

import com.furnitureshop.importer.entity.ImporterDetail;
import com.furnitureshop.product.dto.variant.GetVariantDto;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Getter
public class GetImporterDetailDto {
    private final GetVariantDto variant;
    private final Integer quantity;
    private final Integer price;

    public GetImporterDetailDto(ImporterDetail importerDetail) {
        this.variant = new GetVariantDto(importerDetail.getVariant());
        this.quantity = importerDetail.getQuantity();
        this.price = importerDetail.getPrice();
    }
}
