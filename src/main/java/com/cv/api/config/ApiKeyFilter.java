package com.cv.api.config;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    private final Map<String, String> keyHashMap;

    public ApiKeyFilter(ApiKeyProperties properties) {
        this.keyHashMap = properties.getKeys().stream()
                .collect(Collectors.toMap(ApiKeyProperties.KeyEntry::getKey, ApiKeyProperties.KeyEntry::getHash));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String apiKey = request.getHeader("X-API-Key");
        String apiValue = request.getHeader("X-API-Value");

        if (apiKey == null || apiValue == null || !isValid(apiKey, apiValue)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Unauthorized: invalid or missing API credentials\"}");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isValid(String apiKey, String apiValue) {
        String storedHash = keyHashMap.get(apiKey);
        if (storedHash == null) return false;
        String computedHash = sha256(apiValue);
        return storedHash.equalsIgnoreCase(computedHash);
    }

    private String sha256(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(value.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }
}
