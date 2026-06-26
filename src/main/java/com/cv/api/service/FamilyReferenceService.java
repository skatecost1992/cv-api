package com.cv.api.service;

import com.cv.api.entity.FamilyReference;
import com.cv.api.repository.FamilyReferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyReferenceService {

    private final FamilyReferenceRepository repository;

    public FamilyReferenceService(FamilyReferenceRepository repository) {
        this.repository = repository;
    }

    public List<FamilyReference> findAll() {
        return repository.findAll();
    }

    public FamilyReference findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Family reference not found: " + id));
    }

    public FamilyReference save(FamilyReference reference) {
        return repository.save(reference);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
