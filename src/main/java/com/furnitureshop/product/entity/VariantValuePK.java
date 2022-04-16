package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
public class VariantValuePK implements Serializable {
    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;

    @Column(name = "option_id")
    @Id
    private Long optionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantValuePK that = (VariantValuePK) o;
        return productId.equals(that.productId) && variantId.equals(that.variantId) && optionId.equals(that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, variantId, optionId);
    }
}

