package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Datos de una formación profesional / académica")
public class EducationDTO {

    @Schema(description = "Identificador (solo lectura, se genera automáticamente)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Institución educativa", example = "Universidad Nacional de Ingeniería", requiredMode = Schema.RequiredMode.REQUIRED)
    private String institution;

    @NotBlank
    @Schema(description = "Título, grado o programa obtenido", example = "Ingeniería de Sistemas", requiredMode = Schema.RequiredMode.REQUIRED)
    private String degree;

    @Schema(description = "Descripción o detalle adicional", example = "Especialización en desarrollo de software y bases de datos")
    private String description;

    @Schema(description = "Fecha de inicio en formato ISO (yyyy-MM-dd)", example = "2016-03-01")
    private String startDate;

    @Schema(description = "Fecha de fin en formato ISO (yyyy-MM-dd). Dejar vacío si está en curso", example = "2021-12-15")
    private String endDate;

    @Schema(description = "Ubicación", example = "Lima, Perú")
    private String location;

    @Schema(description = "Orden de aparición (menor = primero). Opcional", example = "1")
    private Integer orderIndex;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Integer getOrderIndex() { return orderIndex; }
    public void setOrderIndex(Integer orderIndex) { this.orderIndex = orderIndex; }
}
