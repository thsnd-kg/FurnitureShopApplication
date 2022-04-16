package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class ProductVariantPK implements Serializable {
    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductVariantPK that = (ProductVariantPK) o;
        return productId.equals(that.productId) && variantId.equals(that.variantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, variantId);
    }
}

