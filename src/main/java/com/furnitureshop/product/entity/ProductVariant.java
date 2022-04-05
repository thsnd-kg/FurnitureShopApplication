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
@IdClass(ProductVariantPK.class)
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariant {
    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "variant_id")
    private Long variantId;

    @Basic
    @Column(name = "sku")
    private Integer sku;

    @Basic
    @Column(name = "price")
    private Integer price;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariant")
    private Collection<VariantValue> variantValues;
}
