package com.cv.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "api-key-auth")
public class ApiKeyProperties {

    private List<KeyEntry> keys;

    public List<KeyEntry> getKeys() { return keys; }
    public void setKeys(List<KeyEntry> keys) { this.keys = keys; }

    public static class KeyEntry {
        private String key;
        private String hash;

        public String getKey() { return key; }
        public void setKey(String key) { this.key = key; }
        public String getHash() { return hash; }
        public void setHash(String hash) { this.hash = hash; }
    }
}
