package com.cv.api.service;

import com.cv.api.entity.ProfessionalReference;
import com.cv.api.repository.ProfessionalReferenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionalReferenceService {

    private final ProfessionalReferenceRepository repository;

    public ProfessionalReferenceService(ProfessionalReferenceRepository repository) {
        this.repository = repository;
    }

    public List<ProfessionalReference> findAll() {
        return repository.findAll();
    }

    public ProfessionalReference findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Professional reference not found: " + id));
    }

    public ProfessionalReference save(ProfessionalReference reference) {
        return repository.save(reference);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
