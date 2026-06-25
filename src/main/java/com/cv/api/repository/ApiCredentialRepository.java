package com.cv.api.repository;

import com.cv.api.entity.ApiCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApiCredentialRepository extends JpaRepository<ApiCredential, Long> {
    Optional<ApiCredential> findByName(String name);
}
