package com.cv.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;

@Schema(description = "Herramienta o tecnología de IA con la que se ha trabajado")
public class AiKnowledgeDTO {

    @Schema(description = "Identificador (solo lectura)", accessMode = Schema.AccessMode.READ_ONLY, example = "1")
    private Long id;

    @NotBlank
    @Schema(description = "Nombre de la IA / herramienta", example = "ChatGPT", requiredMode = Schema.RequiredMode.REQUIRED)
    private String name;

    @NotBlank
    @Schema(description = "Nivel de dominio", example = "Avanzado", requiredMode = Schema.RequiredMode.REQUIRED)
    private String level;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
