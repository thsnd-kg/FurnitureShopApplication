package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.CreateImporterDto;
import com.furnitureshop.importer.entity.Importer;

import java.util.List;

public interface ImporterService {
    List<Importer> getImports();
    Importer getImport(Long importId);
    Importer createImport(CreateImporterDto dto);
}
