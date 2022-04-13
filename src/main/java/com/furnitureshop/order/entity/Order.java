package com.furnitureshop.order.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;


@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status", length = 50)
    private String status;

    @OneToMany(mappedBy = "order")
    private Collection<OrderDetail> orderDetails;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Voucher voucher;
}
