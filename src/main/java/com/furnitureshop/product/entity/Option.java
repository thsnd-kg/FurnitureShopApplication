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
@IdClass(OptionPK.class)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "options")
public class Option {
    @Column(name = "option_id")
    @Id
    private Long optionId;

    @Column(name = "category_id")
    @Id
    private Long categoryId;

    @Column(name = "option_name", length = 50)
    private String optionName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id",
            referencedColumnName = "category_id",
            insertable = false,
            updatable = false,
            nullable = false)
    private Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "option")
    private Collection<VariantValue> variantValues;
}
