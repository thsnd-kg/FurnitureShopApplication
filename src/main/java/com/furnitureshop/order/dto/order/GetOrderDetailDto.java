package com.furnitureshop.order.dto.order;

import com.furnitureshop.order.entity.OrderDetail;
import lombok.Getter;

@Getter
public class GetOrderDetailDto {
    private final Long variantId;
    private final Integer quantity;

    public GetOrderDetailDto(OrderDetail orderDetail) {
        this.variantId = orderDetail.getVariant().getVariantId();
        this.quantity = orderDetail.getQuantity();
    }
}
