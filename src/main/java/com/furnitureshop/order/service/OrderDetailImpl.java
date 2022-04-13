package com.furnitureshop.order.service;

import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailImpl implements OrderDetailService {
    private final OrderRepository repository;

    @Autowired
    public OrderDetailImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<OrderDetail> getOrderDetails() {
        return null;
    }
}
