package com.furnitureshop.product.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OptionPK implements Serializable {
    @Column(name = "option_id")
    @Id
    private Long optionId;

    @Column(name = "category_id")
    @Id
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OptionPK optionPK = (OptionPK) o;
        return optionId.equals(optionPK.optionId) && categoryId.equals(optionPK.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, categoryId);
    }
}
