package com.cv.api.controller;

import com.cv.api.dto.FamilyReferenceDTO;
import com.cv.api.entity.FamilyReference;
import com.cv.api.service.FamilyReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Referencias Familiares", description = "Gestión de las referencias familiares del CV")
@RestController
@RequestMapping("/api/family-references")
public class FamilyReferenceController {

    private final FamilyReferenceService service;

    public FamilyReferenceController(FamilyReferenceService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las referencias familiares")
    @GetMapping
    public List<FamilyReference> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una referencia familiar por id")
    @GetMapping("/{id}")
    public ResponseEntity<FamilyReference> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva referencia familiar")
    @PostMapping
    public ResponseEntity<FamilyReference> create(@Valid @RequestBody FamilyReferenceDTO dto) {
        FamilyReference ref = new FamilyReference();
        applyDto(ref, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ref));
    }

    @Operation(summary = "Actualizar una referencia familiar existente")
    @PutMapping("/{id}")
    public ResponseEntity<FamilyReference> update(@PathVariable Long id, @Valid @RequestBody FamilyReferenceDTO dto) {
        FamilyReference ref = service.findById(id);
        applyDto(ref, dto);
        return ResponseEntity.ok(service.save(ref));
    }

    @Operation(summary = "Eliminar una referencia familiar por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void applyDto(FamilyReference ref, FamilyReferenceDTO dto) {
        ref.setName(dto.getName());
        ref.setRelationship(dto.getRelationship());
        ref.setProfession(dto.getProfession());
        ref.setPhone(dto.getPhone());
        ref.setEmail(dto.getEmail());
    }
}
