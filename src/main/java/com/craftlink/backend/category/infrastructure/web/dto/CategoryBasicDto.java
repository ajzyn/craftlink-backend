package com.craftlink.backend.category.infrastructure.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryBasicDto extends CategorySummaryDto {

  private String imageKey;
  private String description;
}
