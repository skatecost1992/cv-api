package com.cv.api.controller;

import com.cv.api.dto.LanguageDTO;
import com.cv.api.entity.Language;
import com.cv.api.service.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Idiomas", description = "Gestión de los idiomas del CV")
@RestController
@RequestMapping("/api/languages")
public class LanguageController {

    private final LanguageService service;

    public LanguageController(LanguageService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los idiomas")
    @GetMapping
    public List<Language> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un idioma por id")
    @GetMapping("/{id}")
    public ResponseEntity<Language> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear un nuevo idioma")
    @PostMapping
    public ResponseEntity<Language> create(@Valid @RequestBody LanguageDTO dto) {
        Language lang = new Language();
        lang.setName(dto.getName());
        lang.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(lang));
    }

    @Operation(summary = "Actualizar un idioma existente")
    @PutMapping("/{id}")
    public ResponseEntity<Language> update(@PathVariable Long id, @Valid @RequestBody LanguageDTO dto) {
        Language lang = service.findById(id);
        lang.setName(dto.getName());
        lang.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(lang));
    }

    @Operation(summary = "Eliminar un idioma por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
