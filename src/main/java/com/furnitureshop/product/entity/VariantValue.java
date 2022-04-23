package com.furnitureshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@IdClass(VariantValuePK.class)
@AllArgsConstructor
@NoArgsConstructor
public class VariantValue {
    @Id
    @JoinColumn(name = "variant_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    ProductVariant productVariant;

    @Id
    @JoinColumn(name = "option_id")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Option option;

    @Column(name = "option_value",
            length = 50)
    String optionValue;
}

