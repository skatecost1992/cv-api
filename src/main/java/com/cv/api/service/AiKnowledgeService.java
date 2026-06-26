package com.cv.api.service;

import com.cv.api.entity.AiKnowledge;
import com.cv.api.repository.AiKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiKnowledgeService {

    private final AiKnowledgeRepository repository;

    public AiKnowledgeService(AiKnowledgeRepository repository) {
        this.repository = repository;
    }

    public List<AiKnowledge> findAll() {
        return repository.findAll();
    }

    public AiKnowledge findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("AI knowledge not found: " + id));
    }

    public AiKnowledge save(AiKnowledge knowledge) {
        return repository.save(knowledge);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
