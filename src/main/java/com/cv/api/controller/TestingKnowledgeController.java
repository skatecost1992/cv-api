package com.cv.api.controller;

import com.cv.api.dto.TestingKnowledgeDTO;
import com.cv.api.entity.TestingKnowledge;
import com.cv.api.service.TestingKnowledgeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/testing-knowledge")
public class TestingKnowledgeController {

    private final TestingKnowledgeService service;

    public TestingKnowledgeController(TestingKnowledgeService service) {
        this.service = service;
    }

    @GetMapping
    public List<TestingKnowledge> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestingKnowledge> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<TestingKnowledge> create(@Valid @RequestBody TestingKnowledgeDTO dto) {
        TestingKnowledge knowledge = new TestingKnowledge();
        knowledge.setName(dto.getName());
        knowledge.setLevel(dto.getLevel());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(knowledge));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestingKnowledge> update(@PathVariable Long id, @Valid @RequestBody TestingKnowledgeDTO dto) {
        TestingKnowledge knowledge = service.findById(id);
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
