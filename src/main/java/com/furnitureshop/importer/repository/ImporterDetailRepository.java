package com.furnitureshop.importer.repository;

import com.furnitureshop.importer.entity.ImporterDetail;
import com.furnitureshop.importer.entity.ImporterDetailPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImporterDetailRepository extends JpaRepository<ImporterDetail, ImporterDetailPK> {
}
