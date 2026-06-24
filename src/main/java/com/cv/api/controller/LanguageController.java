package com.cv.api.controller;

import com.cv.api.dto.LanguageDTO;
import com.cv.api.entity.Language;
import com.cv.api.service.LanguageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @GetMapping
    public List<Language> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Language> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Language> create(@Valid @RequestBody LanguageDTO dto) {
        Language lang = new Language();
        lang.setName(dto.getName());
        lang.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lang));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Language> update(@PathVariable Long id, @Valid @RequestBody LanguageDTO dto) {
        Language lang = service.findById(id);
        lang.setName(dto.getName());
        lang.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(lang));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
