package com.furnitureshop.product.entity;

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

    @Column(name = "price")
    private Integer price;

    @Column(name = "image", length = 300)
    private String image;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sku")
    private String sku;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @OneToMany(mappedBy = "productVariant")
    private Collection<VariantValue> variantValues;
}
