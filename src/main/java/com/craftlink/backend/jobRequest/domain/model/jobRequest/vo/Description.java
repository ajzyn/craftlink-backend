package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record Description(String value) {

  public Description {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_DESCRIPTION", "Description is required");
    }

    if (value.length() > 500) {
      throw new DomainException("DESCRIPTION_TOO_LONG", "Description is too long");
    }
  }
}
