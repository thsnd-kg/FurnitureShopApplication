package com.furnitureshop.order.entity;

import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Voucher extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voucher")
    private Long voucherId;

    @Column(name = "voucher_desc",
            length = 100)
    private String voucherDesc;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "valid_date")
    private LocalDateTime validDate;

    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;

    @Column(name = "voucher_value")
    private Integer voucherValue;

    @Column(name = "capped_at")
    private Integer cappedAt;

    @Column(name = "is_deleted",
            columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "voucher")
    @Column(name = "order_id")
    private List<Order> orders;
}
