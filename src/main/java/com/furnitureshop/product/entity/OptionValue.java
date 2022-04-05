package com.furnitureshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
/*@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "product_id", "option_id", "value_name" })
})*/
@IdClass(OptionValuePK.class)
@NoArgsConstructor
@AllArgsConstructor
public class OptionValue {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "option_id")
    private Long optionId;

    @Id
    @Column(name = "value_id")
    private Long valueId;

    @Basic
    @Column(name = "value_name", length = 50)
    private String valueName;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "option_id", referencedColumnName = "option_id", nullable = false, insertable = false, updatable = false)
    })
    private Option option;

    @OneToMany(mappedBy = "optionValue")
    private Collection<VariantValue> variantValues;
}
