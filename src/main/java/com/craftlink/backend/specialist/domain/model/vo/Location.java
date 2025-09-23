package com.craftlink.backend.specialist.domain.model.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public record Location(String value) {

  public Location {
    if (StringUtils.isBlank(value)) {
      throw new DomainException("INCORRECT_LOCATION",
          "Location cannot be empty",
          Map.of("value", "null or blank"));
    }
  }
}