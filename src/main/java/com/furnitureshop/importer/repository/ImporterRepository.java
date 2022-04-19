package com.furnitureshop.importer.repository;

import com.furnitureshop.importer.entity.Importer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImporterRepository extends JpaRepository<Importer, Long> {
}
