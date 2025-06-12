package com.craftlink.backend.shared.properties;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
@Getter
@Setter
@Validated
public class JwtProperties {

    @NotNull
    private long expirationTime;

    @NotBlank
    @Size(min = 32)
    private String secret;
}
