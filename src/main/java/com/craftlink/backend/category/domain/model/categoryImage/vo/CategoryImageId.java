package com.craftlink.backend.category.domain.model.categoryImage.vo;

import com.craftlink.backend.infrastructure.exceptions.custom.DomainException;
import java.util.Map;
import java.util.UUID;

public record CategoryImageId(UUID value) {

  public CategoryImageId {
    if (value == null) {
      throw new DomainException(
          "CATEGORY_IMAGE_ID_NULL",
          "Category image id cannot be null",
          Map.of("value", "null")
      );
    }
  }

  public static CategoryImageId newId() {
    return new CategoryImageId(UUID.randomUUID());
  }
}
