package com.furnitureshop.importer.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.importer.repository.ImportDetailRepository;
import com.furnitureshop.importer.service.ImportDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ImportDetailController {
    private final ImportDetailService service;

    @Autowired
    public ImportDetailController(ImportDetailService service) {
        this.service = service;
    }

    @GetMapping
    public Object getImportDetails() {
        return ResponseHandler.getResponse(service.getImportDetails(), HttpStatus.OK);
    }
}
