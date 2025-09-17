package com.craftlink.backend.category.domain.model.category.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainViolation;
import java.util.Map;
import java.util.UUID;

public record CategoryId(UUID value) {

  public CategoryId {
    if (value == null) {
      throw new DomainViolation(
          "CATEGORY_ID_NULL",
          "Category id cannot be null",
          Map.of("value", "null")
      );
    }
  }

  public static CategoryId newId() {
    return new CategoryId(UUID.randomUUID());
  }
}