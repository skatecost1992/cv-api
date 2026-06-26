package com.cv.api.service;

import com.cv.api.entity.ComplementaryStudy;
import com.cv.api.repository.ComplementaryStudyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComplementaryStudyService {

    private final ComplementaryStudyRepository repository;

    public ComplementaryStudyService(ComplementaryStudyRepository repository) {
        this.repository = repository;
    }

    public List<ComplementaryStudy> findAll() {
        return repository.findAll();
    }

    public ComplementaryStudy findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Complementary study not found: " + id));
    }

    public ComplementaryStudy save(ComplementaryStudy study) {
        return repository.save(study);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
