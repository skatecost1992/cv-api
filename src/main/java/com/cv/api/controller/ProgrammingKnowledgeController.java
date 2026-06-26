package com.cv.api.controller;

import com.cv.api.dto.ProgrammingKnowledgeDTO;
import com.cv.api.entity.ProgrammingKnowledge;
import com.cv.api.service.ProgrammingKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Conocimientos de Programación", description = "Gestión de los conocimientos de programación del CV")
@RestController
@RequestMapping("/api/programming-knowledge")
public class ProgrammingKnowledgeController {

    private final ProgrammingKnowledgeService service;

    public ProgrammingKnowledgeController(ProgrammingKnowledgeService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los conocimientos de programación")
    @GetMapping
    public List<ProgrammingKnowledge> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un conocimiento de programación por id")
    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingKnowledge> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear un nuevo conocimiento de programación")
    @PostMapping
    public ResponseEntity<ProgrammingKnowledge> create(@Valid @RequestBody ProgrammingKnowledgeDTO dto) {
        ProgrammingKnowledge knowledge = new ProgrammingKnowledge();
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(knowledge));
    }

    @Operation(summary = "Actualizar un conocimiento de programación existente")
    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingKnowledge> update(@PathVariable Long id, @Valid @RequestBody ProgrammingKnowledgeDTO dto) {
        ProgrammingKnowledge knowledge = service.findById(id);
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(knowledge));
    }

    @Operation(summary = "Eliminar un conocimiento de programación por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
