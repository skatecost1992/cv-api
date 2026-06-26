package com.cv.api.controller;

import com.cv.api.dto.CertificationDTO;
import com.cv.api.entity.Certification;
import com.cv.api.service.CertificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Certificaciones", description = "Gestión de las certificaciones del CV")
@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las certificaciones")
    @GetMapping
    public List<Certification> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una certificación por id")
    @GetMapping("/{id}")
    public ResponseEntity<Certification> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva certificación")
    @PostMapping
    public ResponseEntity<Certification> create(@Valid @RequestBody CertificationDTO dto) {
        Certification cert = new Certification();
        cert.setName(dto.getName());
        cert.setIssuer(dto.getIssuer());
        cert.setDate(dto.getDate() != null ? LocalDate.parse(dto.getDate()) : null);
        cert.setUrl(dto.getUrl());
        cert.setDescription(dto.getDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cert));
    }

    @Operation(summary = "Actualizar una certificación existente")
    @PutMapping("/{id}")
    public ResponseEntity<Certification> update(@PathVariable Long id, @Valid @RequestBody CertificationDTO dto) {
        Certification cert = service.findById(id);
        cert.setName(dto.getName());
        cert.setIssuer(dto.getIssuer());
        cert.setDate(dto.getDate() != null ? LocalDate.parse(dto.getDate()) : null);
        cert.setUrl(dto.getUrl());
        cert.setDescription(dto.getDescription());
        return ResponseEntity.ok(service.save(cert));
    }

    @Operation(summary = "Eliminar una certificación por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
