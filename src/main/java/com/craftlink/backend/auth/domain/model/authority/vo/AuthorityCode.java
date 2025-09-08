package com.craftlink.backend.auth.domain.model.authority.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record AuthorityCode(String value) {

  public AuthorityCode {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation(
          "EMPTY_AUTHORITY_CODE",
          "Authority code cannot be empty",
          Map.of("value", value)
      );
    }
  }
}
