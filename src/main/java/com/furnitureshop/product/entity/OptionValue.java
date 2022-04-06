package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "value_name", length = 50)
    private String valueName;

    @Column(name = "sku", length = 50)
    private String sku;

    @Column(name = "image", length = 300)
    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "option_id", referencedColumnName = "option_id", nullable = false, insertable = false, updatable = false)
    })
    private Option option;

    @OneToMany(mappedBy = "optionValue")
    private Collection<VariantValue> variantValues;
}
