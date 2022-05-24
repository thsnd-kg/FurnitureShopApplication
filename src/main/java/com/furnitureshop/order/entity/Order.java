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
    private Integer totalPrice;

    public Integer getTotalPrice() {
        if (voucher != null) {
            int sum = orderDetails.stream().mapToInt(i -> i.getVariant().getPrice() * i.getQuantity()).sum();
            int deal = sum * voucher.getVoucherValue() / 100;
            System.out.println(deal);
            System.out.println(sum);
            System.out.println(voucher.getCappedAt());
            if (deal > voucher.getCappedAt()) {
                sum = sum - voucher.getCappedAt();
            } else {
                sum = sum - deal;
            }

            return sum;
        }

        return orderDetails.stream().mapToInt(i -> i.getVariant().getPrice() * i.getQuantity()).sum();
    }

    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderDetail> orderDetails = new HashSet<>();
}
