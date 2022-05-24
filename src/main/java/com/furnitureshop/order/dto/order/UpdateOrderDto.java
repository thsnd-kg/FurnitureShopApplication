package com.furnitureshop.order.dto.order;

import com.furnitureshop.order.entity.OrderStatus;
import com.furnitureshop.order.entity.PaymentStatus;
import lombok.Getter;

@Getter
public class UpdateOrderDto {
    private final Long orderId;
    private final OrderStatus orderStatus;
    private final PaymentStatus paymentStatus;

    public UpdateOrderDto(Long orderId, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }
}
