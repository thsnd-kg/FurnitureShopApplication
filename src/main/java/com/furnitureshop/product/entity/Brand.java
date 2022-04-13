package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Brand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brand_id", updatable = false)
    private Long brandId;

    @NotBlank
    private String brandName;

    private String description;

    @NotNull
    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(  mappedBy = "brand")
    private Collection<Product> products;
}
