package com.furnitureshop.product.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", updatable = false)
    protected Long categoryId;

    @Column(name = "category_name", length = 50)
    private String categoryName;

    @Column(name = "category_description", length = 100)
    private String categoryDescription;

    @Column(columnDefinition = "boolean default false")
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private Collection<Product> products;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private Collection<Category> children;

    @OneToMany(mappedBy = "category")
    private Collection<Option> options;
}
