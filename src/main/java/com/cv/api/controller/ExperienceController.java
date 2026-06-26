package com.cv.api.controller;

import com.cv.api.dto.ExperienceDTO;
import com.cv.api.entity.Experience;
import com.cv.api.service.ExperienceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Experiencia", description = "Gestión de la experiencia laboral del CV")
@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService service;

    public ExperienceController(ExperienceService service) {
        this.service = service;
    }

    @Operation(summary = "Listar toda la experiencia laboral")
    @GetMapping
    public List<Experience> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una experiencia por id")
    @GetMapping("/{id}")
    public ResponseEntity<Experience> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva experiencia laboral")
    @PostMapping
    public ResponseEntity<Experience> create(@Valid @RequestBody ExperienceDTO dto) {
        Experience exp = new Experience();
        exp.setCompany(dto.getCompany());
        exp.setRole(dto.getRole());
        exp.setDescription(dto.getDescription());
        exp.setStartDate(dto.getStartDate() != null ? LocalDate.parse(dto.getStartDate()) : null);
        exp.setEndDate(dto.getEndDate() != null ? LocalDate.parse(dto.getEndDate()) : null);
        exp.setLocation(dto.getLocation());
        exp.setOrderIndex(dto.getOrderIndex());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(exp));
    }

    @Operation(summary = "Actualizar una experiencia laboral existente")
    @PutMapping("/{id}")
    public ResponseEntity<Experience> update(@PathVariable Long id, @Valid @RequestBody ExperienceDTO dto) {
        Experience exp = service.findById(id);
        exp.setCompany(dto.getCompany());
        exp.setRole(dto.getRole());
        exp.setDescription(dto.getDescription());
        exp.setStartDate(dto.getStartDate() != null ? LocalDate.parse(dto.getStartDate()) : null);
        exp.setEndDate(dto.getEndDate() != null ? LocalDate.parse(dto.getEndDate()) : null);
        exp.setLocation(dto.getLocation());
        exp.setOrderIndex(dto.getOrderIndex());
        return ResponseEntity.ok(service.save(exp));
    }

    @Operation(summary = "Eliminar una experiencia laboral por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
