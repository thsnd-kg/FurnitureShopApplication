package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.ImporterDetailDto;
import com.furnitureshop.importer.entity.ImporterDetail;
import com.furnitureshop.importer.repository.ImporterDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImporterDetailServiceImpl implements ImporterDetailService {
    private final ImporterDetailRepository repository;

    @Autowired
    public ImporterDetailServiceImpl(ImporterDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public ImporterDetail createImportDetail(Long importId, ImporterDetailDto dto) {
        ImporterDetail importDetail = new ImporterDetail();

        importDetail.setImportId(importId);
        importDetail.setProductId(dto.getProductId());
        importDetail.setVariantId(dto.getVariantId());
        importDetail.setPrice(dto.getPrice());
        importDetail.setQuantity(dto.getQuantity());

        return repository.save(importDetail);
    }
}
