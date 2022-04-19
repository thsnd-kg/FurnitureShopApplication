package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.ImporterDetailDto;
import com.furnitureshop.importer.dto.ImporterDto;
import com.furnitureshop.importer.entity.Importer;

import java.util.List;

public interface ImporterService {
    List<Importer> getImports();
    Importer getImport(Long importId);
    Importer createImport(ImporterDto dto, List<ImporterDetailDto> importDetailDtos);
}
