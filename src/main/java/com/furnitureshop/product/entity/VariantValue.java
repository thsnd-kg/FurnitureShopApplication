package com.furnitureshop.product.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VariantValue {
    @EmbeddedId
    VariantValueKey id;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    ProductVariant productVariant;

    @ManyToOne
    @MapsId("optionId")
    @JoinColumn(name = "option_id")
    Option option;

    String optionValue;
}
