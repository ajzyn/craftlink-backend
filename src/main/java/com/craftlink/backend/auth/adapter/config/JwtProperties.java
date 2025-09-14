package com.craftlink.backend.auth.adapter.config;

import jakarta.validation.Valid;
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

  @Valid
  private AccessToken accessToken = new AccessToken();

  @NotBlank
  @Size(min = 32)
  private String secret;

  @Getter
  @Setter
  public static class AccessToken {

    @NotNull
    private long expirationSeconds;
  }
}
