package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class VariantValue {
    @JsonIgnore
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private VariantValuePK id;

    @ManyToOne
    @MapsId("variantId")
    @JoinColumn(name = "variant_id")
    private ProductVariant productVariant;

    @ManyToOne
    @MapsId("optionId")
    @JoinColumn(name = "option_id")
    private Option option;

    @Column(name = "option_value")
    private String optionValue;

    @Column(name = "option_image",
            length = 300)
    private String optionImage;
}
