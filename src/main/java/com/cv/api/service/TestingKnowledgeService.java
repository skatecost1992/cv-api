package com.cv.api.service;

import com.cv.api.entity.TestingKnowledge;
import com.cv.api.repository.TestingKnowledgeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestingKnowledgeService {

    private final TestingKnowledgeRepository repository;

    public TestingKnowledgeService(TestingKnowledgeRepository repository) {
        this.repository = repository;
    }

    public List<TestingKnowledge> findAll() {
        return repository.findAll();
    }

    public TestingKnowledge findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Testing knowledge not found: " + id));
    }

    public TestingKnowledge save(TestingKnowledge knowledge) {
        return repository.save(knowledge);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
