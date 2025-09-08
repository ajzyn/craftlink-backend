package com.craftlink.backend.jobRequest.domain.model.vo;

import com.craftlink.backend.shared.exceptions.DomainViolation;
import org.apache.commons.lang3.StringUtils;

public record City(String value) {

  public City {
    if (StringUtils.isBlank(value)) {
      throw new DomainViolation("INCORRECT_CITY", "City required");
    }
  }
}
