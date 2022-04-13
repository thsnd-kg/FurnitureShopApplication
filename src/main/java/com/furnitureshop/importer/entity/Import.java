package com.furnitureshop.importer.entity;

import com.furnitureshop.common.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collection;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Import extends BaseEntity {
    @Column(length = 100)
    private String description;

    @OneToMany(mappedBy = "anImport")
    private Collection<ImportDetail> importDetails;
}
