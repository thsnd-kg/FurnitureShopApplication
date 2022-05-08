package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.order.CreateOrderDetailDto;
import com.furnitureshop.order.dto.order.CreateOrderDto;
import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.repository.OrderRepository;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final VariantService variantService;
    private final VoucherService voucherService;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, VariantService variantService, VoucherService voucherService) {
        this.repository = repository;
        this.variantService = variantService;
        this.voucherService = voucherService;
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrder(Long orderId) {
        return repository.getOne(orderId);
    }

    @Override
    public Order createOrder(CreateOrderDto orderDto) {
        Order order = new Order();

        order.setStatus(orderDto.getStatus());

        if (orderDto.getVoucherId() != null) {
            Voucher voucher = voucherService.getVoucherById(orderDto.getVoucherId());
            order.setVoucher(voucher);
        }

        for (CreateOrderDetailDto orderDetailDto : orderDto.getOrderDetails()) {
            OrderDetail orderDetail = new OrderDetail();

            Variant variant = variantService.getVariantById(orderDetailDto.getVariantId());

            orderDetail.setOrder(order);
            orderDetail.setVariant(variant);
            orderDetail.setQuantity(orderDetailDto.getQuantity());

            order.getOrderDetails().add(orderDetail);
        }

        return repository.save(order);
    }
}
