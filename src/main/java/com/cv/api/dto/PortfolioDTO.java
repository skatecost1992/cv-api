package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Proyecto de portafolio")
public class PortfolioDTO {

    @Schema(description = "Identificador (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre del proyecto", example = "Aprende Idiomas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @Schema(description = "Descripción del proyecto", example = "Aplicación web para practicar idiomas con ejercicios interactivos")
    private String description;

    @Schema(description = "Tecnologías utilizadas, separadas por coma", example = "React, Spring Boot, MongoDB")
    private String technologies;

    @Schema(description = "URL de la demo o sitio publicado", example = "https://aprende-idiomas.vercel.app")
    private String url;

    @Schema(description = "URL del repositorio del proyecto", example = "https://github.com/cristhian/aprende-idiomas")
    private String repositoryUrl;

    @Schema(description = "Fecha en formato ISO (yyyy-MM-dd)", example = "2024-03-10")
    private String date;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public String getRepositoryUrl() { return repositoryUrl; }
    public void setRepositoryUrl(String repositoryUrl) { this.repositoryUrl = repositoryUrl; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}
