package com.cv.api.service;

import com.cv.api.entity.Portfolio;
import com.cv.api.repository.PortfolioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PortfolioService {

    private final PortfolioRepository repository;

    public PortfolioService(PortfolioRepository repository) {
        this.repository = repository;
    }

    public List<Portfolio> findAll() {
        return repository.findAll();
    }

    public Portfolio findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Portfolio item not found: " + id));
    }

    public Portfolio save(Portfolio portfolio) {
        return repository.save(portfolio);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
