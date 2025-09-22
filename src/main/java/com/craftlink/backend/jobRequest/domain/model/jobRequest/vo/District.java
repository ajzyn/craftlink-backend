package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record District(String value) {

  public District {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_DISTRICT", "District name cannot be blank");
    }
  }
}
