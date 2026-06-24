package com.cv.api.controller;

import com.cv.api.dto.ExperienceDTO;
import com.cv.api.entity.Experience;
import com.cv.api.service.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/experiences")
public class ExperienceController {

    private final ExperienceService service;

    public ExperienceController(ExperienceService service) {
        this.service = service;
    }

    @GetMapping
    public List<Experience> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
