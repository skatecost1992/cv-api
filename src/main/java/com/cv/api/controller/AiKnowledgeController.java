package com.cv.api.controller;

import com.cv.api.dto.AiKnowledgeDTO;
import com.cv.api.entity.AiKnowledge;
import com.cv.api.service.AiKnowledgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "IA", description = "Gestión de las herramientas/tecnologías de IA del CV")
@RestController
@RequestMapping("/api/ai-knowledge")
public class AiKnowledgeController {

    private final AiKnowledgeService service;

    public AiKnowledgeController(AiKnowledgeService service) {
        this.service = service;
    }

    @Operation(summary = "Listar todas las IA")
    @GetMapping
    public List<AiKnowledge> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Obtener una IA por id")
    @GetMapping("/{id}")
    public ResponseEntity<AiKnowledge> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Crear una nueva IA")
    @PostMapping
    public ResponseEntity<AiKnowledge> create(@Valid @RequestBody AiKnowledgeDTO dto) {
        AiKnowledge knowledge = new AiKnowledge();
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(knowledge));
    }

    @Operation(summary = "Actualizar una IA existente")
    @PutMapping("/{id}")
    public ResponseEntity<AiKnowledge> update(@PathVariable Long id, @Valid @RequestBody AiKnowledgeDTO dto) {
        AiKnowledge knowledge = service.findById(id);
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(knowledge));
    }

    @Operation(summary = "Eliminar una IA por id", description = "Devuelve 204 No Content si el borrado fue exitoso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
