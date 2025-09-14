package com.craftlink.backend.auth.adapter.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@Setter
@ConfigurationProperties("auth.cookie.refresh-token")
public class RefreshTokenCookieProperties {

  private long expirationSeconds;
  private String domain;
  private String path;
  private boolean isSecure;
  private boolean httpOnly;
  private String sameSite;
}
