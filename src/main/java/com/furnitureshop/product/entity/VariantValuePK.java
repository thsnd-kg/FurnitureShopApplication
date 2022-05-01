package com.furnitureshop.product.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VariantValuePK implements Serializable {
    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "option_id")
    private Long optionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantValuePK that = (VariantValuePK) o;
        return variantId.equals(that.variantId) && optionId.equals(that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variantId, optionId);
    }
}