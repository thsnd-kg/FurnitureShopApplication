package com.furnitureshop.product.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VariantValueKey implements Serializable {
    @Column(name = "variant_id")
    Long variantId;

    @Column(name = "option_id")
    Long optionId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariantValueKey that = (VariantValueKey) o;
        return variantId.equals(that.variantId) && optionId.equals(that.optionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(variantId, optionId);
    }
}