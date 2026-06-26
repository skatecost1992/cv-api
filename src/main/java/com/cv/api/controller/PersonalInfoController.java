package com.cv.api.controller;

import com.cv.api.dto.PersonalInfoDTO;
import com.cv.api.entity.PersonalInfo;
import com.cv.api.service.PersonalInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Información Personal", description = "Datos personales del CV (registro único)")
@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {

    private final PersonalInfoService service;

    public PersonalInfoController(PersonalInfoService service) {
        this.service = service;
    }

    @Operation(summary = "Obtener la información personal", description = "Devuelve 404 si aún no se ha registrado")
    @GetMapping
    public ResponseEntity<PersonalInfo> get() {
        return service.get()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Crear o actualizar la información personal", description = "Upsert: guarda el único registro de info personal")
    @PutMapping
    public ResponseEntity<PersonalInfo> save(@Valid @RequestBody PersonalInfoDTO dto) {
        PersonalInfo info = new PersonalInfo();
        info.setName(dto.getName());
        info.setTitle(dto.getTitle());
        info.setEmail(dto.getEmail());
        info.setPhone(dto.getPhone());
        info.setLocation(dto.getLocation());
        info.setSummary(dto.getSummary());
        info.setLinkedin(dto.getLinkedin());
        info.setGithub(dto.getGithub());
        info.setWebsite(dto.getWebsite());
        return ResponseEntity.ok(service.save(info));
    }
}
