package com.cv.api.service;

import com.cv.api.entity.Education;
import com.cv.api.repository.EducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationService {

    private final EducationRepository repository;

    public EducationService(EducationRepository repository) {
        this.repository = repository;
    }

    public List<Education> findAll() {
        return repository.findAllByOrderByOrderIndexAscStartDateDesc();
    }

    public Education findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Education not found: " + id));
    }

    public Education save(Education education) {
        return repository.save(education);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
