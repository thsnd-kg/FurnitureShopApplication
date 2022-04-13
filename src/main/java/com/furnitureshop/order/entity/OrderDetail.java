package com.furnitureshop.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
@IdClass(OrderDetailPK.class)
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    @Column(name = "order_id")
    @Id
    private Long orderId;

    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Order order;
}
