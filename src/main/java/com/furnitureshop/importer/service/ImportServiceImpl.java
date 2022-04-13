package com.furnitureshop.importer.service;

import com.furnitureshop.importer.entity.Import;
import com.furnitureshop.importer.repository.ImportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImportServiceImpl implements ImportService {
    private final ImportRepository repository;

    @Autowired
    public ImportServiceImpl(ImportRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Import> getImports() {
        return repository.findAll();
    }
}
