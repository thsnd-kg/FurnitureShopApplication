package com.furnitureshop.importer.service;

import com.furnitureshop.importer.dto.ImporterDetailDto;
import com.furnitureshop.importer.dto.ImporterDto;
import com.furnitureshop.importer.entity.Importer;
import com.furnitureshop.importer.entity.ImporterDetail;
import com.furnitureshop.importer.repository.ImporterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImporterServiceImpl implements ImporterService {
    private final ImporterRepository repository;
    private final ImporterDetailService importDetailService;

    @Autowired
    public ImporterServiceImpl(ImporterRepository repository, ImporterDetailService importDetailService) {
        this.repository = repository;
        this.importDetailService = importDetailService;
    }

    @Override
    public List<Importer> getImports() {
        return repository.findAll();
    }

    @Override
    public Importer getImport(Long importId) {
        return repository.findById(importId).orElse(null);
    }

    @Override
    public Importer createImport(ImporterDto dto, List<ImporterDetailDto> importDetailDtos) {
        Importer importer = new Importer();

        importer.setImportId(dto.getImportId());
        importer.setImportDesc(dto.getImportDesc());

        Long importId = repository.save(importer).getImportId();
        List<ImporterDetail> importerDetails = new ArrayList<>();

        importDetailDtos.forEach(importDetailDto -> importerDetails.add(importDetailService.createImportDetail(importId, importDetailDto)));

        importer.setImportDetails(importerDetails);

        importer.setTotalPrice(importer
                .getImportDetails()
                .stream()
                .mapToInt(ImporterDetail::getPrice)
                .sum());

        return repository.save(importer);
    }
}
