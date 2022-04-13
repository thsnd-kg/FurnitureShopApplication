package com.furnitureshop.order.entity;

import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class Voucher extends BaseEntity {
    @Column(name = "description", length = 50)
    private String description;

    @Column(name = "valid_date")
    private Date validDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "value")
    private Integer value;

    @Column(name = "min_pay")
    private Integer minPay;

    @Column(name = "capped_at")
    private Integer cappedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "voucher")
    private Collection<Order> orders;
}
