package com.craftlink.backend.auth.domain.model.user.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record AuthorityCode(String value) {

  public AuthorityCode {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("AUTHORITY_CODE_EMPTY", "Authority code cannot be empty");
    }
  }
}
