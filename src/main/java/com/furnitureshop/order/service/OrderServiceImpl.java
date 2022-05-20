package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.order.CreateOrderDto;
import com.furnitureshop.order.entity.Order;
import com.furnitureshop.order.entity.OrderDetail;
import com.furnitureshop.order.entity.OrderStatus;
import com.furnitureshop.order.entity.Voucher;
import com.furnitureshop.order.repository.OrderDetailRepository;
import com.furnitureshop.order.repository.OrderRepository;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.service.VariantService;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderDetailRepository orderDetailRepository;
    private final VariantService variantService;
    private final VoucherService voucherService;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, OrderDetailRepository orderDetailRepository, VariantService variantService, VoucherService voucherService, UserService userService) {
        this.repository = repository;
        this.orderDetailRepository = orderDetailRepository;
        this.variantService = variantService;
        this.voucherService = voucherService;
        this.userService = userService;
    }

    @Override
    public List<Order> getOrders() {
        return repository.findAll();
    }

    @Override
    public Order getOrder(Long orderId) {
        return repository.getOne(orderId);
    }

    public Order createCart() {
        Order order = new Order();

        order.setStatus(OrderStatus.PUTTING);
        order.setUser(userService.getProfile());

        return repository.save(order);
    }

    @Override
    public Order createCart(CreateOrderDto dto) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        List<OrderDetail> orderDetails = orderDetailRepository.getById_OrderId(cart.getOrderId());
        orderDetailRepository.deleteAll(orderDetails);

        dto.getOrderDetails().forEach(orderDetailDto -> {
            OrderDetail orderDetail = new OrderDetail();
            Variant variant = variantService.getVariantById(orderDetailDto.getVariantId());
            orderDetail.setOrder(cart);
            orderDetail.setVariant(variant);
            orderDetail.setQuantity(orderDetailDto.getQuantity());

            cart.getOrderDetails().add(orderDetail);
        });

        Voucher voucher = voucherService.getVoucherById(dto.getVoucherId());

        cart.setVoucher(voucher);

        return repository.save(cart);
    }

    @Override
    public List<Order> getYourOrders() {
        return repository.findByUser(userService.getProfile()).stream().filter(o -> !o.getStatus().equals(OrderStatus.PUTTING)).collect(Collectors.toList());
    }

    private Order findCart() {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElse(null);

        if (cart == null) {
            throw new IllegalStateException("Cart not found!");
        }

        return cart;
    }

    @Override
    public Order checkout() {
        Order cart = findCart();

        cart.setStatus(OrderStatus.PURCHASED);

        return repository.save(cart);
    }
}
