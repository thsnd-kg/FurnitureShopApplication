package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(VariantValuePK.class)
public class VariantValue {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "product_id")
    private Long productId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "variant_id")
    private Long variantId;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "option_id")
    private Long optionId;

    @Basic
    @Column(name = "value_id")
    private Long valueId;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "variant_id", referencedColumnName = "variant_id", nullable = false, insertable = false, updatable = false)
    })
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "option_id", referencedColumnName = "option_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "value_id", referencedColumnName = "value_id", nullable = false, insertable = false, updatable = false)
    })
    private OptionValue optionValue;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "option_id", referencedColumnName = "option_id", nullable = false, insertable = false, updatable = false)
    })
    private Option option;
}
