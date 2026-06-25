package com.cv.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

/**
 * Maneja el cifrado asimétrico RSA-OAEP con SHA-256.
 * - La clave pública se entrega al cliente (puede cifrar con ella).
 * - La clave privada (solo en el servidor) descifra el valor recibido.
 */
@Service
public class RsaService {

    private static final String TRANSFORMATION = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    private final PrivateKey privateKey;
    private final String publicKeyBase64;

    public RsaService(@Value("${rsa.private-key}") String privateKeyBase64,
                      @Value("${rsa.public-key}") String publicKeyBase64) throws Exception {
        this.publicKeyBase64 = publicKeyBase64;
        byte[] pkcs8 = Base64.getDecoder().decode(privateKeyBase64);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(pkcs8));
    }

    /** Descifra un texto Base64 cifrado con RSA-OAEP(SHA-256) usando la clave privada. */
    public String decrypt(String ciphertextBase64) throws Exception {
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        OAEPParameterSpec oaep = new OAEPParameterSpec(
                "SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.DECRYPT_MODE, privateKey, oaep);
        byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(ciphertextBase64));
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    public String getPublicKeyBase64() {
        return publicKeyBase64;
    }
}
