package com.cv.api.controller;

import com.cv.api.dto.TestingKnowledgeDTO;
import com.cv.api.entity.TestingKnowledge;
import com.cv.api.service.TestingKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Conocimientos de Testing", description = "Gestión de los conocimientos de pruebas/testing del CV")
@RestController
@RequestMapping("/api/testing-knowledge")
public class TestingKnowledgeController {

    private final TestingKnowledgeService service;

    public TestingKnowledgeController(TestingKnowledgeService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todos los conocimientos de testing")
    @GetMapping
    public List<TestingKnowledge> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener un conocimiento de testing por id")
    @GetMapping("/{id}")
    public ResponseEntity<TestingKnowledge> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear un nuevo conocimiento de testing")
    @PostMapping
    public ResponseEntity<TestingKnowledge> create(@Valid @RequestBody TestingKnowledgeDTO dto) {
        TestingKnowledge knowledge = new TestingKnowledge();
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(knowledge));
    }

    @Operation(summary = "Actualizar un conocimiento de testing existente")
    @PutMapping("/{id}")
    public ResponseEntity<TestingKnowledge> update(@PathVariable Long id, @Valid @RequestBody TestingKnowledgeDTO dto) {
        TestingKnowledge knowledge = service.findById(id);
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(knowledge));
    }

    @Operation(summary = "Eliminar un conocimiento de testing por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
