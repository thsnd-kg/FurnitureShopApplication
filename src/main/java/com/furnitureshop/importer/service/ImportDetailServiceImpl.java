package com.furnitureshop.importer.service;

import com.furnitureshop.importer.entity.ImportDetail;
import com.furnitureshop.importer.repository.ImportDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportDetailServiceImpl implements ImportDetailService {
    private final ImportDetailRepository repository;

    @Autowired
    public ImportDetailServiceImpl(ImportDetailRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<ImportDetail> getImportDetails() {
        return repository.findAll();
    }
}
