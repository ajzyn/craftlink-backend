package com.craftlink.backend.auth.domain.model.authority.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record AuthorityName(String value) {

  public AuthorityName {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation(
          "EMPTY_AUTHORITY_NAME",
          "Authority name cannot be empty",
          Map.of("value", value)
      );
    }

    if (value.length() > 128) {
      throw new DomainViolation(
          "TOO_LONG_AUTHORITY_NAME",
          "Authority name should have less than 128 characters long"
      );
    }
  }


}
