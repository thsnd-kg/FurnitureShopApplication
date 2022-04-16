package com.furnitureshop.product.entity;

import com.furnitureshop.common.entity.BaseEntity;
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
public class VariantValue extends BaseEntity {
    @Column(name = "product_id")
    @Id
    private Long productId;

    @Column(name = "variant_id")
    @Id
    private Long variantId;

    @Column(name = "option_id")
    @Id
    private Long optionId;

    @Column(name = "option_value")
    private String optionValue;

    @Column(name = "option_image")
    private String optionImage;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "product_id",
                    referencedColumnName = "product_id",
                    nullable = false,
                    insertable = false,
                    updatable = false),
            @JoinColumn(name = "variant_id",
                    referencedColumnName = "variant_id",
                    nullable = false,
                    insertable = false,
                    updatable = false)
    })
    private ProductVariant productVariant;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "option_id",
                    referencedColumnName = "option_id",
                    nullable = false,
                    insertable = false,
                    updatable = false),
            @JoinColumn(name = "category_id",
                    referencedColumnName = "category_id",
                    nullable = false,
                    insertable = false,
                    updatable = false)
    })
    private Option option;
}

