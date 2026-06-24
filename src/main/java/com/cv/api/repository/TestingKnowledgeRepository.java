package com.cv.api.repository;

import com.cv.api.entity.TestingKnowledge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestingKnowledgeRepository extends JpaRepository<TestingKnowledge, Long> {
}
