package com.cv.api.entity;

import javax.persistence.*;

@Entity
@Table(name = "api_credential")
public class ApiCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    // Valor en claro (texto plano), tal como se solicitó.
    @Column(nullable = false)
    private String secretValue;

    public ApiCredential() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSecretValue() { return secretValue; }
    public void setSecretValue(String secretValue) { this.secretValue = secretValue; }
}
