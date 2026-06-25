package com.cv.api.config;

import com.cv.api.entity.ApiCredential;
import com.cv.api.repository.ApiCredentialRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Carga la credencial inicial en la base de datos (en claro) si aún no existe.
 * Funciona igual en H2 (local) y PostgreSQL (Render).
 */
@Component
public class CredentialSeeder implements CommandLineRunner {

    private final ApiCredentialRepository repository;

    public CredentialSeeder(ApiCredentialRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.findByName("app-cv").isEmpty()) {
            ApiCredential cred = new ApiCredential();
            cred.setName("app-cv");
            cred.setSecretValue("mi-valor-secreto");
            repository.save(cred);
        }
    }
}
