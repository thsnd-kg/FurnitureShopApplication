package com.furnitureshop.order.dto.order;

import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.product.dto.variant.GetVariantDto;
import lombok.Getter;

@Getter
public class GetOrderDetailDto {
    private final GetVariantDto variant;
    private final Integer quantity;

    public GetOrderDetailDto(OrderDetail orderDetail) {
        this.variant = new GetVariantDto(orderDetail.getVariant());
        this.quantity = orderDetail.getQuantity();
    }
}
