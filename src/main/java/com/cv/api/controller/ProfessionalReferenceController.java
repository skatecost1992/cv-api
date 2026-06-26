package com.cv.api.controller;

import com.cv.api.dto.ProfessionalReferenceDTO;
import com.cv.api.entity.ProfessionalReference;
import com.cv.api.service.ProfessionalReferenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Referencias Profesionales", description = "Gestión de las referencias profesionales del CV")
@RestController
@RequestMapping("/api/professional-references")
public class ProfessionalReferenceController {

    private final ProfessionalReferenceService service;

    public ProfessionalReferenceController(ProfessionalReferenceService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las referencias profesionales")
    @GetMapping
    public List<ProfessionalReference> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una referencia profesional por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalReference> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva referencia profesional")
    @PostMapping
    public ResponseEntity<ProfessionalReference> create(@Valid @RequestBody ProfessionalReferenceDTO dto) {
        ProfessionalReference ref = new ProfessionalReference();
        applyDto(ref, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(ref));
    }

    @Operation(summary = "Actualizar una referencia profesional existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalReference> update(@PathVariable Long id, @Valid @RequestBody ProfessionalReferenceDTO dto) {
        ProfessionalReference ref = service.findById(id);
        applyDto(ref, dto);
        return ResponseEntity.ok(service.save(ref));
    }

    @Operation(summary = "Eliminar una referencia profesional por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void applyDto(ProfessionalReference ref, ProfessionalReferenceDTO dto) {
        ref.setName(dto.getName());
        ref.setProfession(dto.getProfession());
        ref.setPhone(dto.getPhone());
        ref.setEmail(dto.getEmail());
    }
}
