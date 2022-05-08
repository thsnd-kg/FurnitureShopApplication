package com.furnitureshop.order.dto.order;

import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.Voucher;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetOrderDto {
    private final Long orderId;
    private final String status;
    private final Voucher voucher;
    private final List<GetOrderDetailDto> orderDetails;

    public GetOrderDto(Order order) {
        this.orderId = order.getOrderId();
        this.status = order.getStatus();
        this.voucher = order.getVoucher();
        this.orderDetails = order.getOrderDetails().isEmpty()
                ? new ArrayList<>()
                : order.getOrderDetails().stream().map(GetOrderDetailDto::new).collect(Collectors.toList());
    }
}
