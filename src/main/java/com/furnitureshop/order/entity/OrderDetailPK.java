package com.furnitureshop.order.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class OrderDetailPK implements Serializable {
    @Column(name = "order_id")
    @Id
    private Long orderId;

    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;
}
