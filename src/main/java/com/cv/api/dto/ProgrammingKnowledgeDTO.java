package com.cv.api.dto;

import javax.validation.constraints.NotBlank;

public class ProgrammingKnowledgeDTO {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String level;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }
}
