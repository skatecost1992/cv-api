package com.cv.api.repository;

import com.cv.api.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationRepository extends JpaRepository<Education, Long> {
    List<Education> findAllByOrderByOrderIndexAscStartDateDesc();
}
