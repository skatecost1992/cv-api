package com.cv.api.controller;

import com.cv.api.dto.EducationDTO;
import com.cv.api.entity.Education;
import com.cv.api.service.EducationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Formación Profesional", description = "Gestión de la formación profesional / académica del CV")
@RestController
@RequestMapping("/api/educations")
public class EducationController {

    private final EducationService service;

    public EducationController(EducationService service) {
        this.service = service;
    }

    @Operation(summary = "Listar toda la formación profesional", description = "Devuelve la lista ordenada por orderIndex y fecha de inicio descendente")
    @GetMapping
    public List<Education> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una formación por id")
    @GetMapping("/{id}")
    public ResponseEntity<Education> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva formación profesional")
    @PostMapping
    public ResponseEntity<Education> create(@Valid @RequestBody EducationDTO dto) {
        Education edu = new Education();
        applyDto(edu, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(edu));
    }

    @Operation(summary = "Actualizar una formación profesional existente")
    @PutMapping("/{id}")
    public ResponseEntity<Education> update(@PathVariable Long id, @Valid @RequestBody EducationDTO dto) {
        Education edu = service.findById(id);
        applyDto(edu, dto);
        return ResponseEntity.ok(service.save(edu));
    }

    @Operation(summary = "Eliminar una formación profesional")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void applyDto(Education edu, EducationDTO dto) {
        edu.setInstitution(dto.getInstitution());
        edu.setDegree(dto.getDegree());
        edu.setDescription(dto.getDescription());
        edu.setStartDate(dto.getStartDate() != null && !dto.getStartDate().isEmpty() ? LocalDate.parse(dto.getStartDate()) : null);
        edu.setEndDate(dto.getEndDate() != null && !dto.getEndDate().isEmpty() ? LocalDate.parse(dto.getEndDate()) : null);
        edu.setLocation(dto.getLocation());
        edu.setOrderIndex(dto.getOrderIndex());
    }
}
