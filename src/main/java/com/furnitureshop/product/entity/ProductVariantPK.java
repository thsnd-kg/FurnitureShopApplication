package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
public class ProductVariantPK implements Serializable {
    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;
}

