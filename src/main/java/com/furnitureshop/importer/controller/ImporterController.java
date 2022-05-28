package com.furnitureshop.importer.controller;

import com.furnitureshop.common.ResponseHandler;
import com.furnitureshop.importer.dto.CreateImporterDto;
import com.furnitureshop.importer.dto.GetImporterDto;
import com.furnitureshop.importer.service.ImporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
@RequestMapping("/api/imports")
public class ImporterController {
    private final ImporterService service;

    @Autowired
    public ImporterController(ImporterService importerService) {
        this.service = importerService;
    }

    @GetMapping
    public Object getImports() {
        try {
            List<GetImporterDto> importers = service.getImports().stream().map(GetImporterDto::new).collect(Collectors.toList());
            return ResponseHandler.getResponse(importers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/{import-id}")
    public Object getImport(@PathVariable("import-id") Long importId) {
        try {
            if (importId == null)
                throw new IllegalStateException("Import id must not be null");

            GetImporterDto importer = new GetImporterDto(service.getImport(importId));
            return ResponseHandler.getResponse(importer, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/report")
    public Object importReport(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end,
                                  @RequestParam String compression) {
        try {
            Map<LocalDate, List<GetImporterDto>> result = service.getImportReport(start, end, compression)
                    .entrySet().stream().collect(
                            Collectors.toMap(Map.Entry::getKey,
                                    e -> e.getValue().stream().map(GetImporterDto::new)
                                            .collect(Collectors.toList())));

            return ResponseHandler.getResponse(result, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public Object createImport(@Valid @RequestBody CreateImporterDto dto, BindingResult errors) {
        try {
            if (errors.hasErrors())
                return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

            GetImporterDto importer = new GetImporterDto(service.createImport(dto));
            return ResponseHandler.getResponse(importer, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseHandler.getResponse(e, HttpStatus.BAD_REQUEST);
        }
    }
}
