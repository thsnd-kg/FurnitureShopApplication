package com.furnitureshop.order.service;

import com.furnitureshop.order.dto.order.CreateOrderDetailDto;
import com.furnitureshop.order.dto.order.UpdateOrderDto;
import com.furnitureshop.order.entity.*;
import com.furnitureshop.order.repository.OrderDetailRepository;
import com.furnitureshop.order.repository.OrderRepository;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.service.VariantService;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
        List<Order> orders = repository.findAll();

        return orders.stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING)).collect(Collectors.toList());
    }

    @Override
    public Order getOrder(Long orderId) {
        Optional<Order> order = repository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalStateException("Order not exists");
        }

        return order.get();
    }

    public Order createCart() {
        Order order = new Order();

        order.setOrderStatus(OrderStatus.PUTTING);
        order.setPaymentStatus(PaymentStatus.UNPAID);
        order.setUser(userService.getProfile());

        return repository.save(order);
    }

    @Override
    public Order addCartItem(CreateOrderDetailDto dto) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(new OrderDetailPK(cart.getOrderId(), dto.getVariantId()));

        if (orderDetailOptional.isPresent()) {
            OrderDetail orderDetail = orderDetailOptional.get();
            orderDetail.setQuantity(dto.getQuantity());

            cart.getOrderDetails().removeIf(o -> Objects.equals(o.getVariant().getVariantId(), dto.getVariantId()));

            cart.getOrderDetails().add(orderDetail);

            return repository.save(cart);
        }

        OrderDetail orderDetail = new OrderDetail();
        Variant variant = variantService.getVariantById(dto.getVariantId());
        orderDetail.setOrder(cart);
        orderDetail.setVariant(variant);
        orderDetail.setQuantity(dto.getQuantity());

        cart.getOrderDetails().add(orderDetail);

        return repository.save(cart);
    }

    @Override
    public Order deleteCartItem(Long variantId) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        if (!cartOptional.isPresent()) {
            throw new IllegalStateException("Cart not found");
        }

        Order cart = cartOptional.get();

        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(new OrderDetailPK(cartOptional.get().getOrderId(), variantId));

        if (!orderDetailOptional.isPresent()) {
            throw new IllegalStateException("Cart item not found");
        }

        OrderDetail orderDetail = orderDetailOptional.get();

        cart.getOrderDetails().remove(orderDetail);

        orderDetailRepository.delete(orderDetail);
        return repository.save(cart);
    }

    @Override
    public Order addVoucher(Long voucherId) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        Voucher voucher = voucherService.getVoucherById(voucherId);

        cart.setVoucher(voucher);

        return repository.save(cart);
    }

    @Override
    public Order removeVoucher() {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        cart.setVoucher(null);

        return repository.save(cart);
    }

    @Override
    public List<Order> getYourOrders() {
        return repository.findByUser(userService.getProfile()).stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING)).collect(Collectors.toList());
    }

    @Override
    public Order getYourCart() {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElse(null);

        if (cart == null) {
            throw new IllegalStateException("Cart not found!");
        }

        return cart;
    }

    @Override
    public Order checkout() {
        Order cart = getYourCart();

        cart.setOrderStatus(OrderStatus.PENDING);
        cart.setCreatedAt(LocalDateTime.now());

        return repository.save(cart);
    }

    @Override
    public Order changeOrderStatus(UpdateOrderDto dto) {
        Order order = getOrder(dto.getOrderId());

        order.setOrderStatus(dto.getOrderStatus());
        order.setPaymentStatus(dto.getPaymentStatus());

        return repository.save(order);
    }
}
