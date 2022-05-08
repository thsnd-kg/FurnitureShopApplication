package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.order.CreateOrderDto;
import com.furnitureshop.order.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrders();

    Order getOrder(Long orderId);

    Order createOrder(CreateOrderDto dto);
}
