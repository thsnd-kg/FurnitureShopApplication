package com.furnitureshop.product.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "product_id", "option_name" })
})
@IdClass(OptionPK.class)
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "option_id")
    private Long optionId;

    @Basic
    @Column(name = "option_name", length = 50)
    private String optionName;

    @OneToMany(mappedBy = "option")
    private Collection<OptionValue> optionValues;

    @OneToMany(mappedBy = "option")
    private Collection<VariantValue> variantValues;
}
