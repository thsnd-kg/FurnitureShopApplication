package com.furnitureshop.order.service;

import com.furnitureshop.common.util.AdjusterUtils;
import com.furnitureshop.order.dto.order.CreateOrderDetailDto;
import com.furnitureshop.order.dto.order.UpdateOrderDto;
import com.furnitureshop.order.entity.*;
import com.furnitureshop.order.repository.OrderDetailRepository;
import com.furnitureshop.order.repository.OrderRepository;
import com.furnitureshop.product.dto.product.GetProductDto;
import com.furnitureshop.product.entity.Variant;
import com.furnitureshop.product.service.ProductService;
import com.furnitureshop.product.service.VariantService;
import com.furnitureshop.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;
    private final OrderDetailRepository orderDetailRepository;
    private final VariantService variantService;
    private final VoucherService voucherService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, OrderDetailRepository orderDetailRepository, VariantService variantService, VoucherService voucherService, UserService userService, ProductService productService) {
        this.repository = repository;
        this.orderDetailRepository = orderDetailRepository;
        this.variantService = variantService;
        this.voucherService = voucherService;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public List<Order> getOrders() {
        List<Order> orders = repository.findAllByIsDeletedFalse();

        return orders.stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING) && !o.getIsDeleted()).collect(Collectors.toList());
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
            Variant variant = variantService.getVariantById(orderDetail.getVariant().getVariantId());

            if (variant.getQuantity() < dto.getQuantity())
                throw new IllegalStateException("Maximum quantity: " + variant.getQuantity().toString());

            orderDetail.setQuantity(dto.getQuantity());

            cart.getOrderDetails().removeIf(o -> Objects.equals(o.getVariant().getVariantId(), dto.getVariantId()));

            cart.getOrderDetails().add(orderDetail);

            return repository.save(cart);
        }

        OrderDetail orderDetail = new OrderDetail();
        Variant variant = variantService.getVariantById(dto.getVariantId());

        if (variant.getQuantity() < dto.getQuantity())
            throw new IllegalStateException("Maximum quantity: " + variant.getQuantity().toString());

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
    public Order addVoucher(String voucherName) {
        List<Order> orderList = repository.findByUser(userService.getProfile());
        Optional<Order> cartOptional = orderList.stream().filter(o -> o.getOrderStatus().equals(OrderStatus.PUTTING)).findFirst();

        Order cart = cartOptional.orElseGet(this::createCart);

        Voucher voucher = voucherService.getVoucherByName(voucherName);

        if (voucher.getAmount() == 0)
            throw new IllegalStateException("Voucher is out of stock");

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
        return repository.findByUser(userService.getProfile()).stream().filter(o -> !o.getOrderStatus().equals(OrderStatus.PUTTING) && !o.getIsDeleted()).collect(Collectors.toList());
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

        if (cart.getVoucher() != null) {
            if (cart.getVoucher().getValidDate().toLocalDate().isAfter(LocalDate.now()))
                throw new IllegalStateException("Voucher cannot use now");

            if (cart.getVoucher().getExpirationDate().toLocalDate().isBefore(LocalDate.now()))
                throw new IllegalStateException("Voucher has expired");

            Integer voucherAmount = cart.getVoucher().getAmount();
            cart.getVoucher().setAmount(voucherAmount - 1);
        }

        cart.getOrderDetails().forEach(orderDetail -> {
            Integer oldQuantity = orderDetail.getVariant().getQuantity();
            orderDetail.getVariant().setQuantity(oldQuantity - orderDetail.getQuantity());
        });

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

    @Override
    public Map<LocalDate, List<Order>> getOrderReport(LocalDate start, LocalDate end, String compression) {
        return new TreeMap<>(repository.findByCreatedAtBetweenOrderByCreatedAt(start.atStartOfDay(), end.atStartOfDay())
                .stream().filter(o -> o.getPaymentStatus().equals(PaymentStatus.PAID))
                .collect(Collectors.groupingBy(item -> item.getCreatedAt().toLocalDate()
                        .with(AdjusterUtils.getAdjuster().get(compression)))));
    }

    @Override
    public Object getBestSeller() {
        List<OrderDetail> orderDetails = orderDetailRepository.findAll().stream().filter(o -> o.getOrder().getOrderStatus().equals(OrderStatus.COMPLETED)).collect(Collectors.toList());
        List<Map<String, Object>> sold = new ArrayList<>();
        LinkedHashMap<Long, Integer> sorted = new LinkedHashMap<>();

        Map<Long, Integer> calc = orderDetails.stream().collect(Collectors
                .groupingBy(o -> o.getVariant().getProduct().getProductId(),
                        Collectors.summingInt(OrderDetail::getQuantity)));

        calc.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
        sorted.forEach((aLong, integer) -> {
            Map<String, Object> temp = new HashMap<>();

            temp.put("product", new GetProductDto(productService.getProductById(aLong)));
            temp.put("quantity_sold", integer);

            sold.add(temp);
        });

        return sold;
    }

    @Override
    public Boolean deleteOrder(Long orderId) {
        Order order = getOrder(orderId);

        order.setIsDeleted(true);
        repository.save(order);

        return true;
    }

    @Override
    public List<Order> getOrdersByOrderStatus(OrderStatus orderStatus) {
        return repository.findAllByOrderStatusAndIsDeletedFalse(orderStatus);
    }

    @Override
    public Object getRevenue() {
        List<Order> orders = repository.findAllByIsDeletedFalse().stream().filter(o -> o.getPaymentStatus() == PaymentStatus.PAID).collect(Collectors.toList());
        return new HashMap<String, Object>(){{
            put("count_paid_orders", orders.size());
            put("revenue", orders.stream().map(Order::getTotalPrice).mapToInt(Integer::intValue).sum());
        }};
    }
}
