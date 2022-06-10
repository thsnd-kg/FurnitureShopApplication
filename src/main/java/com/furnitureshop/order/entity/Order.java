package com.furnitureshop.order.entity;

import com.furnitureshop.common.entity.BaseEntity;
import com.furnitureshop.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PUTTING;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    @Transient
    Integer discount;

    public Integer getDiscount() {
        if (voucher == null) return 0;

        int sum = getTotalPrice();
        int deal = sum * voucher.getVoucherValue() / 100;

        if (deal > voucher.getCappedAt())
            return voucher.getCappedAt();

        return deal;
    }

    @Transient
    private Integer totalPrice;

    public Integer getTotalPrice() {
        return orderDetails.stream().mapToInt(i -> i.getVariant().getPrice() * i.getQuantity()).sum();
    }

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private Boolean isDeleted = false;
}
