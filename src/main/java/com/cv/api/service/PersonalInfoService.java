package com.cv.api.service;

import com.cv.api.entity.PersonalInfo;
import com.cv.api.repository.PersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonalInfoService {

    private final PersonalInfoRepository repository;

    public PersonalInfoService(PersonalInfoRepository repository) {
        this.repository = repository;
    }

    public Optional<PersonalInfo> get() {
        return repository.findAll().stream().findFirst();
    }

    public PersonalInfo save(PersonalInfo info) {
        Optional<PersonalInfo> existing = get();
        if (existing.isPresent()) {
            PersonalInfo current = existing.get();
            current.setName(info.getName());
            current.setTitle(info.getTitle());
            current.setEmail(info.getEmail());
            current.setPhone(info.getPhone());
            current.setLocation(info.getLocation());
            current.setSummary(info.getSummary());
            current.setLinkedin(info.getLinkedin());
            current.setGithub(info.getGithub());
            current.setWebsite(info.getWebsite());
            return repository.save(current);
        }
        return repository.save(info);
    }
}
