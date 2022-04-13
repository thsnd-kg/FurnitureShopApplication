package com.furnitureshop.importer.repository;

import com.furnitureshop.importer.entity.ImportDetail;
import com.furnitureshop.importer.entity.ImportDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImportDetailRepository extends JpaRepository<ImportDetail, ImportDetailPK> {
}
