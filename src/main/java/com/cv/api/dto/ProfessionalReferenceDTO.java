package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Referencia profesional")
public class ProfessionalReferenceDTO {

    @Schema(description = "Identificador (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre completo de la referencia", example = "Juan Pérez", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Profesión u ocupación", example = "Gerente de Tecnología")
    private String profession;

    @Schema(description = "Teléfono de contacto", example = "+57 3001234567")
    private String phone;

    @Schema(description = "Correo electrónico", example = "juan.perez@empresa.com")
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getProfession() { return profession; }
    public void setProfession(String profession) { this.profession = profession; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
