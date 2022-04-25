package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.ImporterDetailDto;
import com.furnitureshop.importer.entity.ImporterDetail;

public interface ImporterDetailService {
    ImporterDetail createImportDetail(Long importId, ImporterDetailDto dto);
}
