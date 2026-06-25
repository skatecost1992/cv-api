package com.cv.api.controller;

import com.cv.api.config.RsaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Tag(name = "Seguridad", description = "Clave pública RSA para cifrar las credenciales antes de enviarlas")
@RestController
public class PublicKeyController {

    private final RsaService rsaService;

    public PublicKeyController(RsaService rsaService) {
        this.rsaService = rsaService;
    }

    @Operation(summary = "Obtener la clave pública RSA",
            description = "Devuelve la clave pública (X.509/SPKI, Base64) con la que el cliente debe cifrar " +
                    "el valor de la credencial usando RSA-OAEP con SHA-256. Endpoint abierto (no requiere auth).")
    @GetMapping("/public-key")
    public Map<String, String> getPublicKey() {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("algorithm", "RSA-OAEP");
        body.put("hash", "SHA-256");
        body.put("format", "X.509/SPKI Base64");
        body.put("publicKey", rsaService.getPublicKeyBase64());
        return body;
    }
}
