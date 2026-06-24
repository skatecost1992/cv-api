package com.cv.api.service;

import com.cv.api.entity.Language;
import com.cv.api.repository.LanguageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguageService {

    private final LanguageRepository repository;

    public LanguageService(LanguageRepository repository) {
        this.repository = repository;
    }

    public List<Language> findAll() {
        return repository.findAll();
    }

    public Language findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found: " + id));
    }

    public Language save(Language language) {
        return repository.save(language);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
