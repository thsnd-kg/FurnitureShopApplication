package com.furnitureshop.importer.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.importer.dto.ImporterDto;
import com.furnitureshop.importer.service.ImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/import")
public class ImporterController {
    private final ImporterService importerService;

    @Autowired
    public ImporterController(ImporterService importerService) {
        this.importerService = importerService;
    }

    @GetMapping
    public Object getImports() {
        try {
            return ResponseHandler.getResponse(importerService.getImports(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{import-id}")
    public Object getImport(@PathVariable("import-id") Long importId) {
        try {
            if (importId == null)
                throw new IllegalStateException("Import id must not be null");

            return ResponseHandler.getResponse(importerService.getImport(importId), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createImport(@Valid @RequestBody ImporterDto importerDto, BindingResult errors) {
        try {
            if(errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            return ResponseHandler.getResponse(importerService.createImport(importerDto, null), HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
