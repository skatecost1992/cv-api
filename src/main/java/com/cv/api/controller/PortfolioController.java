package com.cv.api.controller;

import com.cv.api.dto.PortfolioDTO;
import com.cv.api.entity.Portfolio;
import com.cv.api.service.PortfolioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Tag(name = "Portafolio", description = "Gestión de los proyectos del portafolio del CV")
@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService service;

    public PortfolioController(PortfolioService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los proyectos del portafolio")
    @GetMapping
    public List<Portfolio> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un proyecto del portafolio por id")
    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear un nuevo proyecto de portafolio")
    @PostMapping
    public ResponseEntity<Portfolio> create(@Valid @RequestBody PortfolioDTO dto) {
        Portfolio portfolio = new Portfolio();
        applyDto(portfolio, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(portfolio));
    }

    @Operation(summary = "Actualizar un proyecto de portafolio existente")
    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> update(@PathVariable Long id, @Valid @RequestBody PortfolioDTO dto) {
        Portfolio portfolio = service.findById(id);
        applyDto(portfolio, dto);
        return ResponseEntity.ok(service.save(portfolio));
    }

    @Operation(summary = "Eliminar un proyecto de portafolio por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private void applyDto(Portfolio portfolio, PortfolioDTO dto) {
        portfolio.setName(dto.getName());
        portfolio.setDescription(dto.getDescription());
        portfolio.setTechnologies(dto.getTechnologies());
        portfolio.setUrl(dto.getUrl());
        portfolio.setRepositoryUrl(dto.getRepositoryUrl());
        portfolio.setDate(dto.getDate() != null && !dto.getDate().isEmpty() ? LocalDate.parse(dto.getDate()) : null);
    }
}
