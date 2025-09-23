package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

public record ProfilePhotoUrl(String value) {

  public ProfilePhotoUrl {
    if (value != null && !value.isBlank()) {
      try {
        URI.create(value).toURL();
      } catch (MalformedURLException e) {
        throw new DomainException("INVALID_PROFILE_PHOTO_URL",
            "Profile photo URL is invalid",
            Map.of("value", value));
      }
    }
  }

  public static ProfilePhotoUrl empty() {
    return new ProfilePhotoUrl(null);
  }
}
