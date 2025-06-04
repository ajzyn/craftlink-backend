package com.craftlink.backend.shared.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
@Getter
@Setter
public class JwtProperties {

    private long expirationTime;
    private String secret;
}
