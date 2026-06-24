package com.cv.api.controller;

import com.cv.api.dto.CertificationDTO;
import com.cv.api.entity.Certification;
import com.cv.api.service.CertificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/certifications")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Certification> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certification> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
