package com.furnitureshop.importer.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.importer.service.ImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/imports")
public class ImportController {
    private final ImportService service;

    @Autowired
    public ImportController(ImportService service) {
        this.service = service;
    }

    @GetMapping
    public Object getImports() {
        return ResponseHandler.getResponse(service.getImports(), HttpStatus.OK);
    }
}
