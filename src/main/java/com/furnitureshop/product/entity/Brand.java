package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Brand")
@NoArgsConstructor
@AllArgsConstructor
public class Brand extends BaseEntity {
    @NotBlank
    private String brandName;
    private String description;

    @NotNull
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL )
    private Set<Product> products;
}
