package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Estudio complementario / curso adicional")
public class ComplementaryStudyDTO {

    @Schema(description = "Identificador (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre del curso o estudio", example = "Scrum Fundamentals", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Institución o plataforma", example = "Platzi")
    private String institution;

    @Schema(description = "Fecha en formato ISO (yyyy-MM-dd)", example = "2024-03-10")
    private String date;

    @Schema(description = "Descripción o detalle adicional", example = "Curso de fundamentos de metodologías ágiles")
    private String description;

    @Schema(description = "URL del certificado o curso", example = "https://platzi.com/certificado/abc")
    private String url;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
}
