package com.craftlink.backend.category.infrastructure.web.dto;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategorySummaryDto {

  private UUID id;
  private String name;
  private String slug;
  private String iconName;
}
