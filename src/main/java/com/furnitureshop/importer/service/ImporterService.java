package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.CreateImporterDto;
import com.furnitureshop.importer.entity.Importer;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ImporterService {
    List<Importer> getImports();
    Importer getImport(Long importId);
    Importer createImport(CreateImporterDto dto);
    Map<LocalDate, List<Importer>> getImportReport(LocalDate start, LocalDate end, String compression);
    Object getTotalCost();
}
