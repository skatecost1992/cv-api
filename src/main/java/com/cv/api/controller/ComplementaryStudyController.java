package com.cv.api.controller;

import com.cv.api.dto.ComplementaryStudyDTO;
import com.cv.api.entity.ComplementaryStudy;
import com.cv.api.service.ComplementaryStudyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Estudios Complementarios", description = "Gestión de los estudios complementarios / cursos adicionales del CV")
@RestController
@RequestMapping("/api/complementary-studies")
public class ComplementaryStudyController {

    private final ComplementaryStudyService service;

    public ComplementaryStudyController(ComplementaryStudyService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los estudios complementarios")
    @GetMapping
    public List<ComplementaryStudy> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un estudio complementario por id")
    @GetMapping("/{id}")
    public ResponseEntity<ComplementaryStudy> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear un nuevo estudio complementario")
    @PostMapping
    public ResponseEntity<ComplementaryStudy> create(@Valid @RequestBody ComplementaryStudyDTO dto) {
        ComplementaryStudy study = new ComplementaryStudy();
        applyDto(study, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(study));
    }

    @Operation(summary = "Actualizar un estudio complementario existente")
    @PutMapping("/{id}")
    public ResponseEntity<ComplementaryStudy> update(@PathVariable Long id, @Valid @RequestBody ComplementaryStudyDTO dto) {
        ComplementaryStudy study = service.findById(id);
        applyDto(study, dto);
        return ResponseEntity.ok(service.save(study));
    }

    @Operation(summary = "Eliminar un estudio complementario por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void applyDto(ComplementaryStudy study, ComplementaryStudyDTO dto) {
        study.setName(dto.getName());
        study.setInstitution(dto.getInstitution());
        study.setDate(dto.getDate() != null && !dto.getDate().isEmpty() ? LocalDate.parse(dto.getDate()) : null);
        study.setDescription(dto.getDescription());
        study.setUrl(dto.getUrl());
    }
}
