package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Referencia familiar")
public class FamilyReferenceDTO {

    @Schema(description = "Identificador (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre completo de la referencia", example = "María Gómez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Parentesco", example = "Madre")
    private String relationship;

    @Schema(description = "Profesión u ocupación", example = "Docente")
    private String profession;

    @Schema(description = "Teléfono de contacto", example = "+57 3007654321")
    private String phone;

    @Schema(description = "Correo electrónico", example = "maria.gomez@correo.com")
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRelationship() { return relationship; }
    public void setRelationship(String relationship) { this.relationship = relationship; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
