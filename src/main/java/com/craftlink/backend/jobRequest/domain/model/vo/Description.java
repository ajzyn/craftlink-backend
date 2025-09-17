package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record Description(String value) {

  public Description {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("INCORRECT_DESCRIPTION", "Description is required");
    }

    if (value.length() > 500) {
      throw new DomainViolation("DESCRIPTION_TOO_LONG", "Description is too long");
    }
  }
}
