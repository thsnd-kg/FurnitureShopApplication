package com.furnitureshop.product.dto.variant;

import com.furnitureshop.product.entity.Variant;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetVariantDto {
    private final Long variantId;
    private final Long productId;
    private final String sku;
    private final Integer price;
    private final Integer importPrice = 1000;
    private final Integer quantity;
    private final String image;
    private final Boolean isDeleted;
    private final List<GetValueDto> options;

    public GetVariantDto(Variant variant) {
        this.variantId = variant.getVariantId();
        this.productId = variant.getProduct().getProductId();
        this.sku = variant.getSku();
        this.price = variant.getPrice();
        this.quantity = variant.getQuantity();
        this.image = variant.getImage();
        this.isDeleted = variant.getIsDeleted();
        this.options = variant.getValues().stream().map(GetValueDto::new).collect(Collectors.toList());
    }
}
