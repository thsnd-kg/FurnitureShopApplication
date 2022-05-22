package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.order.CreateOrderDetailDto;
import com.furnitureshop.order.dto.order.CreateOrderDto;
import com.furnitureshop.order.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order getOrder(Long orderId);

    Order addCartItem(CreateOrderDetailDto dto);

    Order deleteCartItem(Long variantId);

    Order addVoucher(Long voucherId);

    Order removeVoucher();

    List<Order> getYourOrders();

    Order checkout();
}
