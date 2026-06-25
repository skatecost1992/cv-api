package com.cv.api.config;

import com.cv.api.entity.ApiCredential;
import com.cv.api.repository.ApiCredentialRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/**
 * Protege los endpoints /api/*.
 * El cliente envía:
 *   - X-API-Key:   el nombre de la credencial (ej. "app-cv")
 *   - X-API-Value: el valor secreto cifrado con RSA-OAEP(SHA-256) en Base64
 * El servidor descifra X-API-Value con su clave privada y lo compara, en tiempo
 * constante, contra el valor en claro almacenado en la base de datos.
 */
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final ApiCredentialRepository credentials;
    private final RsaService rsaService;

    public ApiKeyFilter(ApiCredentialRepository credentials, RsaService rsaService) {
        this.credentials = credentials;
        this.rsaService = rsaService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String apiKey = request.getHeader("X-API-Key");
        String encryptedValue = request.getHeader("X-API-Value");

        if (apiKey == null || encryptedValue == null || !isValid(apiKey, encryptedValue)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized: invalid or missing API credentials\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isValid(String apiKey, String encryptedValue) {
        ApiCredential credential = credentials.findByName(apiKey).orElse(null);
        if (credential == null) return false;
        try {
            String decrypted = rsaService.decrypt(encryptedValue);
            return MessageDigest.isEqual(
                    decrypted.getBytes(StandardCharsets.UTF_8),
                    credential.getSecretValue().getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            // Cualquier fallo de descifrado (valor manipulado, padding inválido, etc.) => no autorizado
            return false;
        }
    }
}
