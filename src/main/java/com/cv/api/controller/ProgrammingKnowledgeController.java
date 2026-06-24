package com.cv.api.controller;

import com.cv.api.dto.ProgrammingKnowledgeDTO;
import com.cv.api.entity.ProgrammingKnowledge;
import com.cv.api.service.ProgrammingKnowledgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/programming-knowledge")
public class ProgrammingKnowledgeController {

    private final ProgrammingKnowledgeService service;

    public ProgrammingKnowledgeController(ProgrammingKnowledgeService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProgrammingKnowledge> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingKnowledge> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<ProgrammingKnowledge> create(@Valid @RequestBody ProgrammingKnowledgeDTO dto) {
        ProgrammingKnowledge knowledge = new ProgrammingKnowledge();
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(knowledge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProgrammingKnowledge> update(@PathVariable Long id, @Valid @RequestBody ProgrammingKnowledgeDTO dto) {
        ProgrammingKnowledge knowledge = service.findById(id);
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.ok(service.save(knowledge));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
