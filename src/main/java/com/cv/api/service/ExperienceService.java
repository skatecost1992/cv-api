package com.cv.api.service;

import com.cv.api.entity.Experience;
import com.cv.api.repository.ExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExperienceService {

    private final ExperienceRepository repository;

    public ExperienceService(ExperienceRepository repository) {
        this.repository = repository;
    }

    public List<Experience> findAll() {
        return repository.findAllByOrderByOrderIndexAscStartDateDesc();
    }

    public Experience findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Experience not found: " + id));
    }

    public Experience save(Experience experience) {
        return repository.save(experience);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
