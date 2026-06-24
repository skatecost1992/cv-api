package com.cv.api.service;

import com.cv.api.entity.Certification;
import com.cv.api.repository.CertificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationService {

    private final CertificationRepository repository;

    public CertificationService(CertificationRepository repository) {
        this.repository = repository;
    }

    public List<Certification> findAll() {
        return repository.findAllByOrderByDateDesc();
    }

    public Certification findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certification not found: " + id));
    }

    public Certification save(Certification certification) {
        return repository.save(certification);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
