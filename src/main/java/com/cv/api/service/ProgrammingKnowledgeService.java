package com.cv.api.service;

import com.cv.api.entity.ProgrammingKnowledge;
import com.cv.api.repository.ProgrammingKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammingKnowledgeService {

    private final ProgrammingKnowledgeRepository repository;

    public ProgrammingKnowledgeService(ProgrammingKnowledgeRepository repository) {
        this.repository = repository;
    }

    public List<ProgrammingKnowledge> findAll() {
        return repository.findAll();
    }

    public ProgrammingKnowledge findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Programming knowledge not found: " + id));
    }

    public ProgrammingKnowledge save(ProgrammingKnowledge knowledge) {
        return repository.save(knowledge);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
