package com.craftlink.backend.jobRequest.domain.model.jobRequest.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import org.apache.commons.lang3.StringUtils;

public record City(String value) {

  public City {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_CITY", "City required");
    }
  }
}
