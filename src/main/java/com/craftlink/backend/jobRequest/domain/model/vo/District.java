package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record District(String value) {

  public District {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("INCORRECT_DISTRICT", "District name cannot be blank");
    }
  }
}
