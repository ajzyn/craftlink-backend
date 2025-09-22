package com.craftlink.backend.category.adapter.in.web.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDetailsResponseDto extends CategorySummaryResponseDto {

  private String imageKey;
  private String description;
}
