package com.cv.api.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PersonalInfoDTO {

    private Long id;

    @NotBlank
    private String name;

    private String title;

    @Email
    private String email;

    private String phone;

    private String location;

    private String summary;

    private String linkedin;

    private String github;

    private String website;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }
    public String getLinkedin() { return linkedin; }
    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
    public String getGithub() { return github; }
    public void setGithub(String github) { this.github = github; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
}
